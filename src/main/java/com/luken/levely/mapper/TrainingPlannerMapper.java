package com.luken.levely.mapper;

import com.luken.levely.dto.request.TrainingPlannerRequestDTO;
import com.luken.levely.dto.response.TrainingPlannerResponseDTO;
import com.luken.levely.model.TrainingPlanner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingPlannerMapper {

    TrainingPlanner toEntity(TrainingPlannerRequestDTO body);

    TrainingPlannerResponseDTO toDTO(TrainingPlanner entity);

}
