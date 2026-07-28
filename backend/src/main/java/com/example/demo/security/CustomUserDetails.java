package com.example.demo.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.SystemUser;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
 
    private final SystemUser systemUser;
 
    public CustomUserDetails(SystemUser systemUser) {
        this.systemUser = systemUser;
    }
    public Long getId() {
        return systemUser.getId();
    }
 
    public SystemUser.UserRole getRole() {
        return systemUser.getRole();
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
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