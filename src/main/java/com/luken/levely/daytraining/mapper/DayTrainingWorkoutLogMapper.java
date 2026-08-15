package com.luken.levely.daytraining.mapper;

import com.luken.levely.daytraining.dto.DayTrainingWorkoutLogRequestDTO;
import com.luken.levely.daytraining.dto.DayTrainingWorkoutLogResponseDTO;
import com.luken.levely.daytraining.model.DayTrainingWorkoutLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DayTrainingWorkoutLogMapper {

    DayTrainingWorkoutLog toEntity(DayTrainingWorkoutLogRequestDTO body);

    @Mapping(source = "dayTraining.id", target = "dayTrainingId")
    @Mapping(source = "workout.id", target = "workoutId")
    DayTrainingWorkoutLogResponseDTO toDTO(DayTrainingWorkoutLog entity);

    @Mapping(source = "dayTraining.id", target = "dayTrainingId")
    @Mapping(source = "workout.id", target = "workoutId")
    List<DayTrainingWorkoutLogResponseDTO> toDTOs(List<DayTrainingWorkoutLog> entities);

}
