package com.luken.levely.user.metrics.mapper;

import com.luken.levely.user.User;
import com.luken.levely.user.metrics.dto.BodyWeightRequestDTO;
import com.luken.levely.user.metrics.dto.BodyWeightResponseDTO;
import com.luken.levely.user.metrics.model.BodyWeight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BodyWeightMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    BodyWeight toEntity(BodyWeightRequestDTO body, User user);

    @Mapping(source = "user.id", target = "userId")
    BodyWeightResponseDTO toDTO(BodyWeight entity);

}
