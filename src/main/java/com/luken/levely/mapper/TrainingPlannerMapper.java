package com.luken.levely.mapper;

import com.luken.levely.dto.request.TrainingPlannerRequestDTO;
import com.luken.levely.dto.response.TrainingPlannerResponseDTO;
import com.luken.levely.model.TrainingPlanner;
import com.luken.levely.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingPlannerMapper {

    default TrainingPlanner toEntity(TrainingPlannerRequestDTO body, User user) {
        return TrainingPlanner.create(body, user);
    }

    TrainingPlannerResponseDTO toDTO(TrainingPlanner entity);
}