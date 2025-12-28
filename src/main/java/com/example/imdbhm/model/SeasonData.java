package com.example.imdbhm.model;

import java.util.List;

public record SeasonData(
    int season,
    List<Episode> episodes
) {}