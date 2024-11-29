package com.rafaelhosaka.rhv.user.utils;

import com.rafaelhosaka.rhv.user.dto.SubscriptionResponse;
import com.rafaelhosaka.rhv.user.dto.UserRequest;
import com.rafaelhosaka.rhv.user.dto.UserResponse;
import com.rafaelhosaka.rhv.user.model.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                user.getCreatedAt(),
                toSubscriptionsResponse(user.getSubscribedUsers())
        );
    }

    public List<SubscriptionResponse> toSubscriptionsResponse(Set<User> subscribedUsers) {
        return subscribedUsers.stream()
                .map(subscribedUser -> new SubscriptionResponse(subscribedUser.getId(), subscribedUser.getName()))
                .collect(Collectors.toList());
    }
}
