package com.luken.levely.user.metrics.controller;

import com.luken.levely.user.metrics.dto.BodyStatsRequestDTO;
import com.luken.levely.user.metrics.dto.BodyWeightRequestDTO;
import com.luken.levely.user.metrics.dto.BodyWeightResponseDTO;
import com.luken.levely.user.metrics.mapper.BodyWeightMapper;
import com.luken.levely.user.metrics.model.BodyWeight;
import com.luken.levely.user.metrics.service.BodyWeightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "bodyweight")
@RequiredArgsConstructor
public class BodyWeightController {

    private final BodyWeightService bodyWeightService;
    private final BodyWeightMapper bodyWeightMapper;

    @GetMapping(value = "/me")
    public ResponseEntity<Page<BodyWeightResponseDTO>> findAllMe(@RequestParam int page, @RequestParam int size) {
        Page<BodyWeight> bodyWeights = bodyWeightService.findAllMe(page, size);
        return ResponseEntity.ok().body(bodyWeights.map(bodyWeightMapper::toDTO));
    }

    @GetMapping(value = "/{bodyWeightId}")
    public ResponseEntity<BodyWeightResponseDTO> findById(@PathVariable UUID bodyWeightId) {
        var bodyWeight = bodyWeightService.findById(bodyWeightId);
        return ResponseEntity.ok().body(bodyWeightMapper.toDTO(bodyWeight));
    }

    @PostMapping
    public ResponseEntity<BodyWeightResponseDTO> createBodyWeight(@RequestBody BodyWeightRequestDTO body) {
        var bodyWeight = bodyWeightService.createBodyWeight(body);

        URI uri = ServletUriComponentsBuilder
                .fromPath("/{bodyWeightId}")
                .buildAndExpand(bodyWeight.getId())
                .toUri();

        return ResponseEntity.created(uri).body(bodyWeightMapper.toDTO(bodyWeight));
    }

    @DeleteMapping(value = "/{bodyWeightId}")
    public ResponseEntity<Void> deleteBodyStats(@PathVariable UUID bodyWeightId) {
        bodyWeightService.deleteBodyWeight(bodyWeightId);
        return ResponseEntity.noContent().build();
    }

}
