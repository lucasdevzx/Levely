package com.luken.levely.mapper;

import com.luken.levely.dto.response.LibraryResponseDTO;
import com.luken.levely.model.Library;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryMapper {

    LibraryResponseDTO toDTO(Library entity);

}
