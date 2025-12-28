package com.example.imdbhm.web;

import com.example.imdbhm.model.ShowHeatmapResponse;
import com.example.imdbhm.service.OmdbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/heatmap")
@CrossOrigin(origins = { "http://localhost:3000", "http://127.0.0.1:3000" })
@RequiredArgsConstructor
public class HeatmapController {

    private final OmdbService omdbService;

    @GetMapping("/{showName}")
    public ResponseEntity<ShowHeatmapResponse> getHeatmap(@PathVariable("showName") String showName) {
        System.out.println("GET /api/heatmap/" + showName);
        try {
            ShowHeatmapResponse resp = omdbService.fetchHeatmap(showName);
            if (resp == null) return ResponseEntity.notFound().build();
            System.out.println("Returning seasons count: " + resp.seasons().size());
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            System.err.println("Error in getHeatmap: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}