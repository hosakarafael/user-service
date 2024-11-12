package com.rafaelhosaka.rhv.user.service;

import com.rafaelhosaka.rhv.user.dto.UserRequest;
import com.rafaelhosaka.rhv.user.model.User;
import com.rafaelhosaka.rhv.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserRequest userRequest){
        var user = new User();
        user.setName(userRequest.name());
        user.setImageUrl(userRequest.imageUrl());
        userRepository.save(user);
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
