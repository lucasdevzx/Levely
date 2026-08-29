package com.luken.levely.social.profile;

import com.luken.levely.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "bio")
    private String bio;

    @Transient
    @Column(name = "last_training")
    private LocalDate lastTraining;

    @Transient
    @Column(name = "complete_trainings")
    private Integer completeTrainings;

    @NonNull
    @OneToOne
    private User user;

}
