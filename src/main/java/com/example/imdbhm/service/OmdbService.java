package com.example.imdbhm.service;

import com.example.imdbhm.model.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class OmdbService {

	private static final String API_KEY = "e0686da";
	private final RestTemplate restTemplate = new RestTemplate();
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Executor executor = Executors.newFixedThreadPool(12);

	public ShowHeatmapResponse fetchHeatmap(String showName) throws Exception {
		System.out.println("Fetching heatmap for: " + showName);
		ShowInfo info = fetchShowInfo(showName);
		if (info == null || info.imdbID() == null) {
			System.out.println("No show info found for: " + showName);
			return null;
		}
		System.out.println("Found show: " + info.title() + " with " + info.totalSeasons() + " seasons");
		List<CompletableFuture<SeasonData>> futures = new ArrayList<>();
		for (int season = 1; season <= info.totalSeasons(); season++) {
			final int s = season;
			futures.add(CompletableFuture.supplyAsync(() -> fetchSeason(info.imdbID(), s), executor));
		}
		CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
		List<SeasonData> seasons = futures.stream()
				.map(CompletableFuture::join)
				.filter(sd -> sd != null)
				.sorted(Comparator.comparingInt(SeasonData::season))
				.toList();
		System.out.println("Returning " + seasons.size() + " seasons");
		return new ShowHeatmapResponse(info.title(), info.plot(), info.poster(), seasons);
	}

	private ShowInfo fetchShowInfo(String showName) throws Exception {
		String q = URLEncoder.encode(showName, StandardCharsets.UTF_8);
		String url = "http://www.omdbapi.com/?t=" + q + "&type=series&apikey=" + API_KEY;
		System.out.println("Fetching: " + url);

		String body = restTemplate.getForObject(url, String.class);
		System.out.println("Response: " + body);
		JsonNode root = objectMapper.readTree(body);
		if (root == null || !"True".equalsIgnoreCase(root.path("Response").asText())) {
			return null;
		}
		String imdbID = root.path("imdbID").asText(null);
		int totalSeasons = parseIntSafe(root.path("totalSeasons").asText(null));
		String title = root.path("Title").asText(null);
		String plot = root.path("Plot").asText(null);
		String poster = root.path("Poster").asText(null);
		return new ShowInfo(imdbID, totalSeasons, title, plot, poster);
	}

	private SeasonData fetchSeason(String imdbID, int season) {
		try {
			String url = "http://www.omdbapi.com/?i=" + imdbID + "&Season=" + season + "&apikey=" + API_KEY;
			String body = restTemplate.getForObject(url, String.class);
			JsonNode root = objectMapper.readTree(body);
			if (root == null || !"True".equalsIgnoreCase(root.path("Response").asText())) {
				return new SeasonData(season, List.of());
			}
			List<Episode> episodes = new ArrayList<>();
			boolean loggedFirst = false;
			for (JsonNode epNode : root.path("Episodes")) {
				int epNum = parseIntSafe(epNode.path("Episode").asText(null));
				if (epNum <= 0) continue;
				String title = epNode.path("Title").asText(null);
				if (title == null || title.isBlank()) title = "Untitled";
				Double rating = parseDoubleSafe(epNode.path("imdbRating").asText(null)); // null when N/A
				Episode ep = new Episode(epNum, title, rating);
				episodes.add(ep);
				if (!loggedFirst) {
					System.out.println("Season " + season + " first ep: " + epNum + " rating=" + rating);
					loggedFirst = true;
				}
			}
			System.out.println("Season " + season + " episodes parsed: " + episodes.size());
			return new SeasonData(season, episodes);
		} catch (Exception e) {
			System.err.println("Error fetching season " + season + ": " + e.getMessage());
			return new SeasonData(season, List.of());
		}
	}

	private int parseIntSafe(String s) {
		try { return s == null ? 0 : Integer.parseInt(s); } catch (Exception e) { return 0; }
	}

	private Double parseDoubleSafe(String s) {
		try { return s == null || "N/A".equalsIgnoreCase(s) ? null : Double.parseDouble(s); } catch (Exception e) { return null; }
	}
}
