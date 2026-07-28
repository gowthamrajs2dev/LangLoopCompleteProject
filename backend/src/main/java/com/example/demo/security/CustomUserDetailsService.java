package com.example.demo.security;

import com.example.demo.entity.SystemUser;
import com.example.demo.repository.SystemUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Not explicitly named in the SRS, but required as a bridge: Spring
 * Security's authentication machinery needs a UserDetailsService to load a
 * user by username during login, and SecurityConfig can't be wired without
 * one.
 *
 * Returns CustomUserDetails (not Spring's built-in User) so the
 * authenticated user's database id is available directly from the security
 * context downstream -- see CustomUserDetails for why that matters.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final SystemUserRepository systemUserRepository;

    public CustomUserDetailsService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = systemUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new CustomUserDetails(user);
    }
}