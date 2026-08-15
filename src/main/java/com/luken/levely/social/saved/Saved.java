package com.luken.levely.social.saved;

import com.luken.levely.social.exception.SocialInteractionException;
import com.luken.levely.common.exception.controller.ApiError;
import com.luken.levely.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "saveds")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Saved {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NonNull
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "saved")
    private List<SavedTrainingPlannerLibrary> savedTrainingPlannerLibraries;

    public static Saved create(User user, boolean existsSaved) {

        if (existsSaved) {
            throw new SocialInteractionException("You can only save it once", ApiError.INTERACTION_INVALID);
        }

        return new Saved(user);
    }

}