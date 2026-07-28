package com.luken.levely.mapper;

import com.luken.levely.dto.response.SetTimeLogResponseDTO;
import com.luken.levely.model.SetTimeLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SetTimeLogMapper {

    @Mapping(source = "dayTrainingWorkoutLog.id", target = "dayTrainingWorkoutLogId")
    SetTimeLogResponseDTO toDTO(SetTimeLog setTimeLog);

    List<SetTimeLogResponseDTO> toDTOs(List<SetTimeLog> setTimeLogs);

}