package com.luken.levely.user.metrics.controller;

import com.luken.levely.user.metrics.dto.BodyStatsRequestDTO;
import com.luken.levely.user.metrics.dto.BodyStatsResponseDTO;
import com.luken.levely.user.metrics.mapper.BodyStatsMapper;
import com.luken.levely.user.metrics.model.BodyStats;
import com.luken.levely.user.metrics.service.BodyStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "bodystats")
@RequiredArgsConstructor
public class BodyStatsController {

    private final BodyStatsService bodyStatsService;
    private final BodyStatsMapper bodyStatsMapper;

    @GetMapping(value = "/me")
    public ResponseEntity<Page<BodyStatsResponseDTO>> findAllMe(@RequestParam int page, @RequestParam int size) {
        Page<BodyStats> bodyStats = bodyStatsService.findAllMe(page, size);
        return ResponseEntity.ok().body(bodyStats.map(bodyStatsMapper::toDTO));
    }

    @GetMapping(value = "/{bodyStatsId}")
    public ResponseEntity<BodyStatsResponseDTO> findById(@PathVariable UUID bodyStatsId) {
        var bodyStats = bodyStatsService.findById(bodyStatsId);
        return ResponseEntity.ok().body(bodyStatsMapper.toDTO(bodyStats));
    }

    @PostMapping
    public ResponseEntity<BodyStatsResponseDTO> createBodyStats(@RequestBody BodyStatsRequestDTO body) {
        var bodyStats = bodyStatsService.createBodyStats(body);

        URI uri = ServletUriComponentsBuilder
                .fromPath("/{bodyStatsId}")
                .buildAndExpand(bodyStats.getId())
                .toUri();

        return ResponseEntity.created(uri).body(bodyStatsMapper.toDTO(bodyStats));
    }

    @DeleteMapping(value = "/{bodyStatsId}")
    public ResponseEntity<Void> deleteBodyStats(@PathVariable UUID bodyStatsId) {
        bodyStatsService.deleteBodyStats(bodyStatsId);
        return ResponseEntity.noContent().build();
    }

}
