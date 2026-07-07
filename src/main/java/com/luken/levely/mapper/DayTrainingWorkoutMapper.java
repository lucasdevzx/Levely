package com.luken.levely.mapper;

import com.luken.levely.dto.request.DayTrainingWorkoutRequestDTO;
import com.luken.levely.dto.response.DayTrainingWorkoutResponseDTO;
import com.luken.levely.model.DayTraining;
import com.luken.levely.model.DayTrainingWorkout;
import com.luken.levely.model.Workout;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DayTrainingWorkoutMapper {

    default DayTrainingWorkout toEntity(DayTraining dayTraining, Workout workout, DayTrainingWorkoutRequestDTO body) {
        return DayTrainingWorkout.create(dayTraining, workout, body);
    }

    @Mapping(source = "dayTraining.id", target = "dayTrainingId")
    @Mapping(source = "workout.id", target = "workoutId")
    DayTrainingWorkoutResponseDTO toDTO(DayTrainingWorkout entity);

}