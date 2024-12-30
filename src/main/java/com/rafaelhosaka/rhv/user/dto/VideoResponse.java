package com.rafaelhosaka.rhv.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoResponse extends Response{
    private Integer id;
    private String title;
    private String description;
    private int views;
    private UserResponse user;
    private Date createdAt;
    private Visibility visibility;
    private String videoUrl;
    private String thumbnailUrl;
}
