package com.example.imdbhm.model;

import java.util.List;

public record ShowHeatmapResponse(
		String title,
		String plot,
		String poster,
		List<SeasonData> seasons
) {}
