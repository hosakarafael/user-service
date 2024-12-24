package com.rafaelhosaka.rhv.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryResponse extends Response{
    private Integer userId;
    private Integer videoId;
    private VideoResponse video;
    private Date watchedAt;
    private boolean isVideoDeleted;
    private boolean isVideoVisible;
}
