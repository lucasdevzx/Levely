package com.luken.levely.library.dto;

import java.util.UUID;

public record LibraryResponseDTO(
        UUID id,
        String name
) {
}
