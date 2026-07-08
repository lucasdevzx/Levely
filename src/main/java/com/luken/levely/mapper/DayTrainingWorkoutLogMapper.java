package com.luken.levely.mapper;

import com.luken.levely.dto.request.DayTrainingWorkoutLogRequestDTO;
import com.luken.levely.dto.response.DayTrainingWorkoutLogResponseDTO;
import com.luken.levely.model.DayTrainingWorkoutLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DayTrainingWorkoutLogMapper {

    DayTrainingWorkoutLog toEntity(DayTrainingWorkoutLogRequestDTO body);

    @Mapping(source = "dayTraining.id", target = "dayTrainingId")
    @Mapping(source = "workout.id", target = "workoutId")
    DayTrainingWorkoutLogResponseDTO toDTO(DayTrainingWorkoutLog entity);

}
