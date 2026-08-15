package com.luken.levely.library;

import com.luken.levely.library.dto.LibraryResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryMapper {

    LibraryResponseDTO toDTO(Library entity);

}
