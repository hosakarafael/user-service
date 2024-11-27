package com.rafaelhosaka.rhv.user.dto;

import java.util.Date;

public record HistoryRequest(Integer userId, Integer videoId, Date watchedAt) {
}
