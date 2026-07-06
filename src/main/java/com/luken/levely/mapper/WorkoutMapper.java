package com.luken.levely.mapper;

import com.luken.levely.dto.request.WorkoutRequestDTO;
import com.luken.levely.dto.response.WorkoutResponseDTO;
import com.luken.levely.model.Workout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {

    default Workout toEntity(WorkoutRequestDTO body) {
        return Workout.create(body);
    }

    WorkoutResponseDTO toDTO(Workout workout);

}
