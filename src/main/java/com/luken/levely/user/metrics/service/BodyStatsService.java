package com.luken.levely.user.metrics.service;

import com.luken.levely.common.exception.ResourceNotFoundException;
import com.luken.levely.common.exception.controller.ApiError;
import com.luken.levely.security.auth.AuthenticatedUser;
import com.luken.levely.user.metrics.dto.BodyStatsRequestDTO;
import com.luken.levely.user.metrics.mapper.BodyStatsMapper;
import com.luken.levely.user.metrics.model.BodyStats;
import com.luken.levely.user.metrics.repository.BodyStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BodyStatsService {

    private final BodyStatsRepository bodyStatsRepository;
    private final BodyStatsMapper bodyStatsMapper;

    private final AuthenticatedUser authenticatedUser;

    public Page<BodyStats> findAllMe(int page, int size) {
        var userId = authenticatedUser.getAuthenticatedUser().getId();
        return bodyStatsRepository.findAllByUserId(PageRequest.of(page, size), userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity body stats not found by user id: " + userId), ApiError.RESOURCE_NOT_FOUND));
    }

    public BodyStats findById(UUID bodyStatsId) {
        return bodyStatsRepository.findById(bodyStatsId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Entity body stats not found by id: " + bodyStatsId), ApiError.RESOURCE_NOT_FOUND));
    }

    public BodyStats createBodyStats(BodyStatsRequestDTO body) {
        var user = authenticatedUser.getAuthenticatedUser();
        var bodystats = bodyStatsMapper.toEntity(body, user);
        return bodyStatsRepository.save(bodystats);
    }

    public void deleteBodyStats(UUID bodyStatsId) {
        var bodyStats = findById(bodyStatsId);
        authenticatedUser.ownershipValidator(bodyStats.getUser());
        bodyStatsRepository.delete(bodyStats);
    }
}
