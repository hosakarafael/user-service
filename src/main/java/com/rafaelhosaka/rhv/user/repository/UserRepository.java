package com.rafaelhosaka.rhv.user.repository;

import com.rafaelhosaka.rhv.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
