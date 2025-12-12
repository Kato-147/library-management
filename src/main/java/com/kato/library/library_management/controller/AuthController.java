package com.kato.library.library_management.controller;

import com.kato.library.library_management.entity.User;
import com.kato.library.library_management.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User createdUser = authService.register(user);
        return ResponseEntity.ok(createdUser);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            String email = body.get("email");
            String password = body.get("password");

            String token = authService.login(email, password);

            return ResponseEntity.ok(Map.of("token", token));

        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Không thể "invalidate" JWT trong server
        // chỉ trả về thông báo để client xoá token đi
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

}
