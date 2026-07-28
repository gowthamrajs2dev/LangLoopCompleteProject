package com.example.demo.repository;

import com.example.demo.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

    // Required for secure authentication lookups during login.
    Optional<SystemUser> findByUsername(String username);

    // Used by AuthService to reject duplicate registrations.
    boolean existsByUsername(String username);
}