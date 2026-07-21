package com.luken.levely.service;

import com.luken.levely.model.Saved;
import com.luken.levely.model.User;
import com.luken.levely.repository.SavedRepository;
import com.luken.levely.security.auth.AuthenticatedUser;
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
