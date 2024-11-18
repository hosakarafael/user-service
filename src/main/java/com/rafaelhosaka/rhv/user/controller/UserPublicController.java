package com.rafaelhosaka.rhv.user.controller;

import com.rafaelhosaka.rhv.user.dto.UserRequest;
import com.rafaelhosaka.rhv.user.dto.UserResponse;
import com.rafaelhosaka.rhv.user.model.User;
import com.rafaelhosaka.rhv.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/pb")
@RequiredArgsConstructor
public class UserPublicController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") Integer id){
        try{
            return ResponseEntity.ok().body(userService.findById(id));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}