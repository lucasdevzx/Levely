package com.luken.levely.mapper;

import com.luken.levely.dto.request.DayTrainingDefaultRequestDTO;
import com.luken.levely.dto.response.DayTrainingResponseDTO;
import com.luken.levely.model.DayTraining;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DayTrainingMapper {

    DayTraining toEntity(DayTrainingDefaultRequestDTO body);

    @Mapping(source = "trainingPlanner.id", target = "trainingPlannerId")
    DayTrainingResponseDTO toDTO(DayTraining entity);

}
