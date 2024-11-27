package com.rafaelhosaka.rhv.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponse{
    private Integer userId;
    private Integer videoId;
    private VideoResponse video;
    private Date watchedAt;
}
