package com.luken.levely.trainingplanner.mapper;

import com.luken.levely.trainingplanner.dto.TrainingPlannerLibraryResponseDTO;
import com.luken.levely.trainingplanner.model.TrainingPlannerLibrary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainingPlannerLibraryMapper {

    @Mapping(source = "trainingPlanner.id", target = "trainingPlannerId")
    @Mapping(source = "library.id", target = "libraryId")
    TrainingPlannerLibraryResponseDTO toDTO(TrainingPlannerLibrary entity);

}