package com.luken.levely.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "likes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NonNull
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "like")
    private List<LikeTrainingPlannerLibrary> likeTrainingPlannerLibraries;

    public static Like create(User user, boolean existsLike) {

        if (existsLike) {
            throw new IllegalArgumentException("You can only like it once");
        }

        return new Like(user);
    }

}