package com.example.imdbhm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Episode(
    @JsonProperty("episode") int episode,
    @JsonProperty("title") String title,
    @JsonProperty("rating") Double rating
) {}