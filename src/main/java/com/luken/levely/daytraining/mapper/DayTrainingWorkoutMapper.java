package com.luken.levely.daytraining.mapper;

import com.luken.levely.daytraining.dto.DayTrainingWorkoutRequestDTO;
import com.luken.levely.daytraining.dto.DayTrainingWorkoutResponseDTO;
import com.luken.levely.daytraining.model.DayTraining;
import com.luken.levely.daytraining.model.DayTrainingWorkout;
import com.luken.levely.workout.model.Workout;
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