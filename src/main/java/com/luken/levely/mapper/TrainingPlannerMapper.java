package com.luken.levely.mapper;

import com.luken.levely.dto.request.TrainingPlannerRequestDTO;
import com.luken.levely.dto.response.TrainingPlannerResponseDTO;
import com.luken.levely.model.TrainingPlanner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingPlannerMapper {

    default TrainingPlanner toEntity(TrainingPlannerRequestDTO body) {
        return TrainingPlanner.create(body);
    }

    TrainingPlannerResponseDTO toDTO(TrainingPlanner entity);
}