package com.luken.levely.social.like;

import com.luken.levely.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public Like createLike(User user, boolean existsLike) {
        var like = Like.create(user, existsLike);
        return likeRepository.save(like);
    }

}