package com.rafaelhosaka.rhv.user.service;

import com.rafaelhosaka.rhv.user.client.VideoClient;
import com.rafaelhosaka.rhv.user.dto.*;
import com.rafaelhosaka.rhv.user.model.History;
import com.rafaelhosaka.rhv.user.model.User;
import com.rafaelhosaka.rhv.user.repository.HistoryRepository;
import com.rafaelhosaka.rhv.user.repository.UserRepository;
import com.rafaelhosaka.rhv.user.utils.HistoryMapper;
import com.rafaelhosaka.rhv.user.utils.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    private final VideoClient videoClient;
    private final UserMapper userMapper;
    private final HistoryMapper historyMapper;

    public UserResponse createUser(UserRequest userRequest){
        var user = userMapper.toUser(userRequest);
        user.setCreatedAt(new Date());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public UserResponse findById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with ID "+id+" not found")
                );
    }

    public UserResponse findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toUserResponse)
                .orElseThrow(
                        () -> new EntityNotFoundException("User with email "+email+" not found")
                );
    }

    public void registerHistory(HistoryRequest historyRequest) {
        var history = new History(historyRequest.userId(), historyRequest.videoId(), new Date());
        historyRepository.save(history);
    }

    public List<HistoryResponse> findHistoryByUserId(Integer userId) {
        Sort sort = Sort.by(Sort.Order.desc("watchedAt"));
        return historyRepository.findByUserId(userId, sort).stream()
                .map(historyMapper::toHistoryResponse)
                .peek(result -> {
                    var userResponse = videoClient.findById(result.getVideoId());
                    result.setVideo(userResponse.getBody());
                }).collect(Collectors.toList());
    }

    public Response subscribeToUser(SubscribeRequest subscribeRequest) throws Exception {
        if(Objects.equals(subscribeRequest.subscriberId(), subscribeRequest.creatorId())){
            throw new Exception("Subscriber and Creator cannot be the same");
        }

        User subscriber = userRepository.findById(subscribeRequest.subscriberId()).orElseThrow(() -> new Exception("subscriber with ID "+subscribeRequest.subscriberId()+" not found"));
        User creator = userRepository.findById(subscribeRequest.creatorId()).orElseThrow(() -> new Exception("creator with ID "+subscribeRequest.creatorId()+" not found"));

        if (!subscriber.getSubscribedUsers().contains(creator)) {
            subscriber.getSubscribedUsers().add(creator);
            userRepository.save(subscriber);
            return new Response("User "+subscriber.getId()+" subscribed to User "+creator.getId()+" successfully");
        }
        throw new Exception("User "+subscriber.getId()+" already subscribed to User "+creator.getId());
    }

    public Response unsubscribeFromUser(SubscribeRequest subscribeRequest) throws Exception {
        if(Objects.equals(subscribeRequest.subscriberId(), subscribeRequest.creatorId())){
            throw new Exception("Subscriber and Creator cannot be the same");
        }

        User subscriber = userRepository.findById(subscribeRequest.subscriberId()).orElseThrow(() -> new Exception("subscriber with ID "+subscribeRequest.subscriberId()+" not found"));
        User creator = userRepository.findById(subscribeRequest.creatorId()).orElseThrow(() -> new Exception("creator with ID "+subscribeRequest.creatorId()+" not found"));

        subscriber.getSubscribedUsers().remove(creator);
        userRepository.save(subscriber);
        return new Response("User "+subscriber.getId()+" unsubscribed from User "+creator.getId()+" successfully");
    }
}
