package com.luken.levely.mapper;

import com.luken.levely.dto.request.WorkoutRequestDTO;
import com.luken.levely.dto.response.WorkoutResponseDTO;
import com.luken.levely.model.User;
import com.luken.levely.model.Workout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    default Workout toEntity(WorkoutRequestDTO body, User user) {
        return Workout.create(body, user);
    }

    WorkoutResponseDTO toDTO(Workout workout);

}
