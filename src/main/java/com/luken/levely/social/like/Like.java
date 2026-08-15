package com.luken.levely.social.like;

import com.luken.levely.social.exception.SocialInteractionException;
import com.luken.levely.common.exception.controller.ApiError;
import com.luken.levely.user.User;
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
            throw new SocialInteractionException("You can only like it once", ApiError.INTERACTION_INVALID);
        }

        return new Like(user);
    }

}