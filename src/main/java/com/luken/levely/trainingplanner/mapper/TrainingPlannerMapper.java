package com.luken.levely.trainingplanner.mapper;

import com.luken.levely.trainingplanner.dto.TrainingPlannerRequestDTO;
import com.luken.levely.trainingplanner.dto.TrainingPlannerResponseDTO;
import com.luken.levely.trainingplanner.model.TrainingPlanner;
import com.luken.levely.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingPlannerMapper {

    default TrainingPlanner toEntity(TrainingPlannerRequestDTO body, User user) {
        return TrainingPlanner.create(body, user);
    }

    TrainingPlannerResponseDTO toDTO(TrainingPlanner entity);
}