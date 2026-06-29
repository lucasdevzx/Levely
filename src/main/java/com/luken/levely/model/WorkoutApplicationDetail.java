package com.luken.levely.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "workout_application_details")
@RequiredArgsConstructor
@EqualsAndHashCode
public class WorkoutApplicationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private Workout workout;

    // TODO: Find need attributes for details
}
