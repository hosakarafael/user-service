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

    public Integer createUser(UserRequest userRequest){
        var user = mapper.toUser(userRequest);
        user.setCreatedAt(new Date());
        return userRepository.save(user).getId();
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
}
