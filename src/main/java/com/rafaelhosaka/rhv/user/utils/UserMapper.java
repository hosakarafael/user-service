package com.rafaelhosaka.rhv.user.utils;

import com.rafaelhosaka.rhv.user.dto.SubscriptionResponse;
import com.rafaelhosaka.rhv.user.dto.UserRequest;
import com.rafaelhosaka.rhv.user.dto.UserResponse;
import com.rafaelhosaka.rhv.user.model.User;
import com.rafaelhosaka.rhv.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final UserRepository userRepository;

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
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .createdAt(user.getCreatedAt())
                .subscribedUsers(toSubscriptionsResponse(user.getSubscribedUsers()))
                .subscribers(userRepository.countSubscribers(user.getId()))
                .build();
    }

    public List<SubscriptionResponse> toSubscriptionsResponse(Set<User> subscribedUsers) {
        if(subscribedUsers == null){
            return new ArrayList<>();
        }
        return subscribedUsers.stream()
                .map(subscribedUser -> new SubscriptionResponse(subscribedUser.getId(), subscribedUser.getName()))
                .collect(Collectors.toList());
    }
}
