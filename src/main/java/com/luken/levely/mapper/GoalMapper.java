package com.luken.levely.mapper;

import com.luken.levely.dto.request.GoalRequestDTO;
import com.luken.levely.dto.response.GoalResponseDTO;
import com.luken.levely.model.Goal;
import com.luken.levely.model.User;
import com.luken.levely.model.Workout;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    default Goal toEntity(GoalRequestDTO body, User user, Workout workout) {
        return Goal.create(
                body.startWeight(),
                body.targetWeight(),
                body.deadline(),
                user,
                workout
        );
    }

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "workout.id", target = "workoutId")
    GoalResponseDTO toDTO(Goal entity);

}
