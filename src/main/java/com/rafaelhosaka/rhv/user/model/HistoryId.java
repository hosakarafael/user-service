package com.rafaelhosaka.rhv.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryId implements Serializable {
    private Integer userId;
    private Integer videoId;
}
