package com.luken.levely.mapper;

import com.luken.levely.dto.response.SetRepLogResponseDTO;
import com.luken.levely.model.SetRepLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SetRepLogMapper {

    @Mapping(source = "dayTrainingWorkoutLog.id", target = "dayTrainingWorkoutLogId")
    SetRepLogResponseDTO toDTO(SetRepLog setRepLog);

}