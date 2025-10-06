package com.ivcstarter.controller;

import com.ivcstarter.entity.User;
import com.ivcstarter.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request.getUsername(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok("Registered successfully. Please activate your account using activation key.");
    }

    @PostMapping("/activate")
    public ResponseEntity<?> activate(@RequestBody ActivateRequest request) {
        User activatedUser = userService.activateUser(request.getUserId(), request.getActivationKey());
        return ResponseEntity.ok("Account activated for user: " + activatedUser.getUsername());
    }

    @Data
    static class RegisterRequest {
        private String username;
        private String email;
        private String password;
    }

    @Data
    static class ActivateRequest {
        private Long userId;
        private String activationKey;
    }
}
