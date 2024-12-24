package com.rafaelhosaka.rhv.user.utils;

import com.rafaelhosaka.rhv.user.dto.HistoryRequest;
import com.rafaelhosaka.rhv.user.dto.HistoryResponse;
import com.rafaelhosaka.rhv.user.dto.UserRequest;
import com.rafaelhosaka.rhv.user.dto.UserResponse;
import com.rafaelhosaka.rhv.user.model.History;
import com.rafaelhosaka.rhv.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class HistoryMapper {
    public History toHistory(HistoryRequest request){
        return new History(request.userId(), request.videoId(), request.watchedAt());
    }

    public HistoryResponse toHistoryResponse(History history){
        return HistoryResponse.builder()
                .userId(history.getUserId())
                .videoId(history.getVideoId())
                .watchedAt(history.getWatchedAt())
                .isVideoVisible(true)
                .isVideoDeleted(false)
                .build();
    }
}
