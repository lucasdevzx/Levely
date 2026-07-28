package com.luken.levely.mapper;

import com.luken.levely.dto.response.SetRepLogResponseDTO;
import com.luken.levely.model.SetRepLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SetRepLogMapper {

    @Mapping(source = "dayTrainingWorkoutLog.id", target = "dayTrainingWorkoutLogId")
    SetRepLogResponseDTO toDTO(SetRepLog setRepLog);

    List<SetRepLogResponseDTO> toDTOs(List<SetRepLog> setRepLogs);

}