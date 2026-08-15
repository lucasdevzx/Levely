package com.luken.levely.user.metrics.model;

import com.luken.levely.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bodystats")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BodyStats {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "height")
    private Double height;

    @Column(name = "body_fat_porcentage")
    private Double bodyFatPercentage;

    @Column(name = "muscle_mass")
    private Double muscleMass;

    @Column(name = "neck")
    private Double neck;

    @Column(name = "chest")
    private Double chest;

    @Column(name = "waist")
    private Double waist;

    @Column(name = "hip")
    private Double hip;

    @Column(name = "left_arm")
    private Double leftArm;

    @Column(name = "right_arm")
    private Double rightArm;

    @Column(name = "left_thigh")
    private Double leftThigh;

    @Column(name = "right_thigh")
    private Double rightThigh;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

}
