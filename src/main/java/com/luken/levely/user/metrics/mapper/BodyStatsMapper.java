package com.luken.levely.user.metrics.mapper;

import com.luken.levely.user.User;
import com.luken.levely.user.metrics.dto.BodyStatsRequestDTO;
import com.luken.levely.user.metrics.dto.BodyStatsResponseDTO;
import com.luken.levely.user.metrics.model.BodyStats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BodyStatsMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    BodyStats toEntity(BodyStatsRequestDTO body, User user);

    @Mapping(source = "user.id", target = "userId")
    BodyStatsResponseDTO toDTO(BodyStats entity);

}
