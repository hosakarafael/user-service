package com.rafaelhosaka.rhv.user.controller;

import com.rafaelhosaka.rhv.user.dto.*;
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
    public ResponseEntity findHistoryByUserId(@PathVariable("user_id") Integer userId){
        try {
            return ResponseEntity.ok().body(userService.findHistoryByUserId(userId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new Response(e.getMessage()));
        }
    }

    @PostMapping("/history")
    public void registerHistory(@RequestBody HistoryRequest historyRequest){
        userService.registerHistory(historyRequest);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<Response> subscribe(@RequestBody SubscribeRequest subscribeRequest){
        try{
            return ResponseEntity.ok().body(userService.subscribeToUser(subscribeRequest));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response(e.getMessage()));
        }
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<Response>  unsubscribe(@RequestBody SubscribeRequest subscribeRequest){
        try{
            return ResponseEntity.ok().body(userService.unsubscribeFromUser(subscribeRequest));
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(new Response(e.getMessage()));
        }
    }
}
