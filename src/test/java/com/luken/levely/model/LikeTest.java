package com.luken.levely.model;

import com.luken.levely.common.exception.SocialInteractionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LikeTest {


    @Test
    void shouldThrowExceptionIfExistsLikeIsTrue() {

        // ARRANGE
        User user = new User();
        boolean existsLike = true;

        // ASSERT + ACT
        assertThrows(SocialInteractionException.class,
                () -> Like.create(user, existsLike));

    }

    @Test
    void shouldReturnLikeAndUserAssociate() {

        // ARRANGE
        User user = new User();
        boolean existsLike = false;

        // ACT
        Like like = Like.create(user, existsLike);

        // ASSERT
        assertNotNull(like);
        assertEquals(user, like.getUser());
    }


}