package com.luken.levely.user.metrics.service;

import com.luken.levely.common.exception.ResourceNotFoundException;
import com.luken.levely.common.exception.controller.ApiError;
import com.luken.levely.security.auth.AuthenticatedUser;
import com.luken.levely.user.metrics.dto.BodyStatsRequestDTO;
import com.luken.levely.user.metrics.dto.BodyWeightRequestDTO;
import com.luken.levely.user.metrics.mapper.BodyStatsMapper;
import com.luken.levely.user.metrics.mapper.BodyWeightMapper;
import com.luken.levely.user.metrics.model.BodyStats;
import com.luken.levely.user.metrics.model.BodyWeight;
import com.luken.levely.user.metrics.repository.BodyStatsRepository;
import com.luken.levely.user.metrics.repository.BodyWeightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BodyWeightService {

    private final BodyWeightRepository bodyWeightRepository;
    private final BodyWeightMapper bodyWeightMapper;

    private final AuthenticatedUser authenticatedUser;

    public Page<BodyWeight> findAllMe(int page, int size) {
        var userId = authenticatedUser.getAuthenticatedUser().getId();
        return bodyWeightRepository.findAllByUserId(PageRequest.of(page, size), userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity body weight not found by user id: " + userId), ApiError.RESOURCE_NOT_FOUND));
    }

    public BodyWeight findById(UUID bodyStatsId) {
        return bodyWeightRepository.findById(bodyStatsId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity body weight not found by id: " + bodyStatsId), ApiError.RESOURCE_NOT_FOUND));
    }

    public BodyWeight createBodyWeight(BodyWeightRequestDTO body) {
        var user = authenticatedUser.getAuthenticatedUser();
        var bodyWeight = bodyWeightMapper.toEntity(body, user);
        return bodyWeightRepository.save(bodyWeight);
    }

    public void deleteBodyWeight(UUID bodyWeightId) {
        var bodyWeight = findById(bodyWeightId);
        authenticatedUser.ownershipValidator(bodyWeight.getUser());
        bodyWeightRepository.delete(bodyWeight);
    }

}
