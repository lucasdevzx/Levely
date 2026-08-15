package com.luken.levely.security.auth;

import com.luken.levely.common.exception.UnauthorizedException;
import com.luken.levely.common.exception.controller.ApiError;
import com.luken.levely.user.User;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new UnauthorizedException("User not authenticated", ApiError.UNAUTHORIZED);
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return  userDetails.getUser();
    }

    public UserDetailsImpl getAuthenticatesUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new UnauthorizedException("User not authenticated", ApiError.UNAUTHORIZED);
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