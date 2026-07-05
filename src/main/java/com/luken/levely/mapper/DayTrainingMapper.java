package com.luken.levely.mapper;

import com.luken.levely.dto.request.DayTrainingRequestDTO;
import com.luken.levely.dto.response.DayTrainingResponseDTO;
import com.luken.levely.model.DayTraining;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DayTrainingMapper {

    default DayTraining toEntity(DayTrainingRequestDTO body) {
        return DayTraining.create(body);
    }

    @Mapping(source = "trainingPlanner.id", target = "trainingPlannerId")
    DayTrainingResponseDTO toDTO(DayTraining entity);

}
