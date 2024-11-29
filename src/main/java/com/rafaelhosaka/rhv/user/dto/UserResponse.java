package com.rafaelhosaka.rhv.user.dto;

import java.util.Date;
import java.util.List;

public record UserResponse(Integer id, String name, String email , String imageUrl, Date createdAt, List<SubscriptionResponse> subscribedUsers) {
}
