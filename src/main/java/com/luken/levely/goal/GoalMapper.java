package com.luken.levely.goal;

import com.luken.levely.goal.dto.GoalRequestDTO;
import com.luken.levely.goal.dto.GoalResponseDTO;
import com.luken.levely.user.User;
import com.luken.levely.workout.model.Workout;
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
