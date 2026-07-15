package com.luken.levely.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "saves")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Saved {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    // User

}
