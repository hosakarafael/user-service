package com.rafaelhosaka.rhv.user.client;

import com.rafaelhosaka.rhv.user.dto.VideoResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "video", url = "http://localhost:8081")
public interface VideoClient {
    @RequestMapping(method = RequestMethod.GET,value = "/api/video/pb/{id}")
    ResponseEntity<VideoResponse> findById(@PathVariable("id") Integer id);
}
