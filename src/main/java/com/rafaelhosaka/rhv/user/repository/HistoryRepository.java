package com.rafaelhosaka.rhv.user.repository;

import com.rafaelhosaka.rhv.user.model.History;
import com.rafaelhosaka.rhv.user.model.HistoryId;
import com.rafaelhosaka.rhv.user.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, HistoryId> {
    List<History> findByUserId(Integer userId, Sort sort);
}
