package com.rafaelhosaka.rhv.user.repository;

import com.rafaelhosaka.rhv.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
