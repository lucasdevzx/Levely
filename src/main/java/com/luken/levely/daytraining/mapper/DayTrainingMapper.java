package com.luken.levely.daytraining.mapper;

import com.luken.levely.daytraining.dto.DayTrainingRequestDTO;
import com.luken.levely.daytraining.dto.DayTrainingResponseDTO;
import com.luken.levely.daytraining.model.DayTraining;
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
