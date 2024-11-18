package com.rafaelhosaka.rhv.user.service;

import com.rafaelhosaka.rhv.user.dto.UserRequest;
import com.rafaelhosaka.rhv.user.dto.UserResponse;
import com.rafaelhosaka.rhv.user.model.User;
import com.rafaelhosaka.rhv.user.repository.UserRepository;
import com.rafaelhosaka.rhv.user.utils.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public UserResponse createUser(UserRequest userRequest){
        var user = mapper.toUser(userRequest);
        user.setCreatedAt(new Date());
        userRepository.save(user);
        return mapper.toUserResponse(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserResponse findById(Integer id) {
        return userRepository.findById(id)
                .map(mapper::toUserResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with ID "+id+" not found")
                );
    }

    public UserResponse findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(mapper::toUserResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with email "+email+" not found")
                );
    }
}
