package com.luken.levely.user.metrics.model;

import com.luken.levely.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bodyweight")
@Getter
@NoArgsConstructor
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BodyWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

}
