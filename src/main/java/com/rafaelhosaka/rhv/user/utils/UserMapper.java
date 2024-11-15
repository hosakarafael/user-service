package com.rafaelhosaka.rhv.user.utils;

import com.rafaelhosaka.rhv.user.dto.UserRequest;
import com.rafaelhosaka.rhv.user.dto.UserResponse;
import com.rafaelhosaka.rhv.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserRequest request){
        return User.builder()
                .id(request.id())
                .name(request.name())
                .email(request.email())
                .imageUrl(request.imageUrl())
                .createdAt(request.createdAt())
                .build();
    }

    public UserResponse toUserResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getImageUrl(),
                user.getCreatedAt()
        );
    }
}
