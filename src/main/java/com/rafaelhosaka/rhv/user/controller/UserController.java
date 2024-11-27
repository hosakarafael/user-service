package com.rafaelhosaka.rhv.user.controller;

import com.rafaelhosaka.rhv.user.dto.HistoryRequest;
import com.rafaelhosaka.rhv.user.dto.HistoryResponse;
import com.rafaelhosaka.rhv.user.dto.UserRequest;
import com.rafaelhosaka.rhv.user.dto.UserResponse;
import com.rafaelhosaka.rhv.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok().body(userService.createUser(userRequest));
    }

    @GetMapping("/email/{email}")
    ResponseEntity<UserResponse> findByEmail(@PathVariable("email") String email){
        try{
            return ResponseEntity.ok().body(userService.findByEmail(email));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/history/{user_id}")
    public ResponseEntity<List<HistoryResponse>> findHistoryByUserId(@PathVariable("user_id") Integer userId){
        return ResponseEntity.ok().body(userService.findHistoryByUserId(userId));
    }

    @PostMapping("/history")
    public void registerHistory(@RequestBody HistoryRequest historyRequest){
        userService.registerHistory(historyRequest);
    }
}
