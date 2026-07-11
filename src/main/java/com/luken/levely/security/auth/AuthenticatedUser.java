package com.luken.levely.security.auth;

import com.luken.levely.common.exception.InvalidActionException;
import com.luken.levely.model.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticatedUser {

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new InvalidActionException("User not authenticated");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return  userDetails.getUser();
    }

    public UserDetailsImpl getAuthenticatesUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new InvalidActionException("User not authenticated");
        }

        return  (UserDetailsImpl) authentication.getPrincipal();
    }

    public boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_" + role));
    }

    public void ownershipValidator(User resourceOwner) {
        var currentUserId = getAuthenticatedUser().getId();
        var ownerResourceId = resourceOwner.getId();

        if (!currentUserId.equals(ownerResourceId)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
    }

    public void adminValidator() {
        if (!hasRole("ADMIN")) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
    }
}