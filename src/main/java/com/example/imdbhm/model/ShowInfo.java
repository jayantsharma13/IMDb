package com.example.imdbhm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ShowInfo(
		@JsonProperty("imdbID") String imdbID,
		@JsonProperty("totalSeasons") int totalSeasons,
		@JsonProperty("Title") String title,
		@JsonProperty("Plot") String plot,
		@JsonProperty("Poster") String poster
) {}
