package com.example.demo.security;

import com.example.demo.entity.SystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Wraps SystemUser so downstream code (controllers, services) can pull the
 * authenticated user's id and role directly off the security context,
 * instead of re-querying SystemUserRepository by username on every request.
 *
 * This isn't in the SRS at all -- it's a practical improvement over using
 * Spring Security's built-in User class, which only exposes
 * username/password/authorities and has no concept of a database id.
 */
public class CustomUserDetails implements UserDetails {

    private final SystemUser systemUser;

    public CustomUserDetails(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    // Convenience accessor: lets StudyController.resolveUserId() and
    // DeckManagementServiceImpl.currentAuthenticatedUser() read the id
    // straight off the principal instead of hitting the repository.
    public Long getId() {
        return systemUser.getId();
    }

    public SystemUser.Role getRole() {
        return systemUser.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Spring Security expects the "ROLE_" prefix for hasRole()/hasAnyRole()
        // checks in @PreAuthorize to match correctly.
        return List.of(new SimpleGrantedAuthority("ROLE_" + systemUser.getRole().name()));
    }

    @Override
    public String getPassword() {
        return systemUser.getPassword();
    }

    @Override
    public String getUsername() {
        return systemUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}