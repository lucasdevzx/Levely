package com.luken.levely.workout.mapper;

import com.luken.levely.workout.dto.WorkoutRequestDTO;
import com.luken.levely.workout.dto.WorkoutResponseDTO;
import com.luken.levely.user.User;
import com.luken.levely.workout.model.Workout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    default Workout toEntity(WorkoutRequestDTO body, User user) {
        return Workout.create(body, user);
    }

    WorkoutResponseDTO toDTO(Workout workout);

}
