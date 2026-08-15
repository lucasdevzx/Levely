package com.luken.levely.service;

import com.luken.levely.common.exception.ResourceNotFoundException;
import com.luken.levely.trainingplanner.dto.TrainingPlannerRequestDTO;
import com.luken.levely.goal.enums.GoalType;
import com.luken.levely.trainingplanner.model.TrainingPlanner;
import com.luken.levely.trainingplanner.service.TrainingPlannerService;
import com.luken.levely.user.User;
import com.luken.levely.trainingplanner.repository.TrainingPlannerRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TrainingPlannerServiceTest {

    @InjectMocks
    private TrainingPlannerService trainingPlannerService;

    @Mock
    private TrainingPlannerRepository trainingPlannerRepository;

    @Mock
    private User user;

    @Mock
    private AuthenticatedUser authenticatedUser;

    @Test
    void shouldReturnPageListOfPlanner() {

        // ARRANGE
        var body = new TrainingPlannerRequestDTO(
                "Strenght",
                GoalType.HYPERTROPHY,
                LocalDate.of(2026, 8, 20),
                LocalDate.of(2026, 9, 20)
        );

        TrainingPlanner trainingPlanner = TrainingPlanner.create(body, user);
        List<TrainingPlanner> trainingPlanners = Collections.nCopies(1, trainingPlanner);
        Page<TrainingPlanner> trainingPlannerPage = new PageImpl<>(trainingPlanners);
        var userId = UUID.randomUUID();

        int page = 0;
        int size = 5;

        BDDMockito.when(authenticatedUser.getAuthenticatedUser()).thenReturn(user);
        BDDMockito.when(user.getId()).thenReturn(userId);
        BDDMockito.when(trainingPlannerRepository.findAllByUserId(userId, PageRequest.of(page, size))).thenReturn(Optional.of(trainingPlannerPage));

        // ACT
        Page<TrainingPlanner> result = trainingPlannerService.findAllMe(page, size);

        // ASSERT
        assertEquals(trainingPlannerPage, result);
    }

    @Test
    void shouldThrowExceptionIfPlannerNotFoundById() {

        // ARRANGE
        UUID trainingPlannerId = UUID.randomUUID();
        BDDMockito.when(trainingPlannerRepository.findById(trainingPlannerId)).thenReturn(Optional.empty());

        // ASSERT + ACT
        assertThrows(ResourceNotFoundException.class,
                () -> trainingPlannerService.findById(trainingPlannerId));
    }

    @Test
    void shouldReturnPlannerById() {

        // ARRANGE
        var body = new TrainingPlannerRequestDTO(
                "Strenght",
                GoalType.HYPERTROPHY,
                LocalDate.of(2026, 8, 20),
                LocalDate.of(2026, 9, 20)
        );

        var user = new User();

        TrainingPlanner trainingPlanner = TrainingPlanner.create(body, user);
        UUID trainingPlannerId = UUID.randomUUID();
        BDDMockito.when(trainingPlannerRepository.findById(trainingPlannerId)).thenReturn(Optional.of(trainingPlanner));

        // ACT
        TrainingPlanner result = trainingPlannerService.findById(trainingPlannerId);

        // ASSERT
        assertEquals(trainingPlanner, result);
    }

}