package com.luken.levely.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "library")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    // When create method for delete Libraru, add delete for TrainingPlannerLibrary
    @OneToMany(mappedBy = "library")
    private List<TrainingPlannerLibrary> trainingPlannerLibraries;

    @NonNull
    private String name;

}