package com.luken.levely.model;

import com.luken.levely.common.exception.SocialInteractionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavedTest {

    @Test
    void shouldThrowExceptionIfExistsSavedIsTrue() {

        // ARRANGE
        User user = new User();
        boolean existsSaved = true;

        // ASSERT + ACT
        assertThrows(SocialInteractionException.class,
                () -> Saved.create(user, existsSaved));

    }

    @Test
    void shouldReturnSavedAndUserAssociate() {

        // ARRANGE
        User user = new User();
        boolean existsSaved = false;

        // ACT
        Saved saved = Saved.create(user, existsSaved);

        // ASSERT
        assertNotNull(saved);
        assertEquals(user, saved.getUser());
    }


}