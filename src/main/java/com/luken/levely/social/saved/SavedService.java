package com.luken.levely.social.saved;

import com.luken.levely.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedService {

    private final SavedRepository savedRepository;

    public Saved createSaved(User user, boolean existsSaved) {
        var saved = Saved.create(user, existsSaved);
        return savedRepository.save(saved);
    }
}
