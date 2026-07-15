package com.luken.levely.mapper;

import com.luken.levely.dto.response.TrainingPlannerLibraryResponseDTO;
import com.luken.levely.model.TrainingPlannerLibrary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainingPlannerLibraryMapper {

    @Mapping(source = "trainingPlanner.id", target = "trainingPlannerId")
    @Mapping(source = "library.id", target = "libraryId")
    TrainingPlannerLibraryResponseDTO toDTO(TrainingPlannerLibrary entity);

}