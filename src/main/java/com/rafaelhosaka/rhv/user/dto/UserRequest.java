package com.rafaelhosaka.rhv.user.dto;

import java.util.Date;

public record UserRequest(Integer id, String name, String email , String imageUrl, Date createdAt) {
}
