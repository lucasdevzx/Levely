package com.luken.levely.service;

import com.luken.levely.model.Like;
import com.luken.levely.model.User;
import com.luken.levely.repository.LikeRepository;
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