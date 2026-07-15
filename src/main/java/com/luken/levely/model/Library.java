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
//@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    private final String name = "Community Library";

    // When create method for delete Library, add delete for TrainingPlannerLibrary
    @OneToMany(mappedBy = "library", fetch = FetchType.LAZY)
    private List<TrainingPlannerLibrary> trainingPlannerLibraries;

    public static Library create() {
        return new Library();
    }

    public void addTrainingPlanner(TrainingPlannerLibrary trainingPlannerLibrary) {
        trainingPlannerLibraries.add(trainingPlannerLibrary);
    }

}