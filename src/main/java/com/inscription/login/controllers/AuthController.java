package com.inscription.login.controllers;

import com.inscription.login.dto.requests.LoginRequest;
import com.inscription.login.dto.responses.LoginResponse;
import com.inscription.login.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletRequest httpReq) {
        try {
            String ip = httpReq.getRemoteAddr();
            String token = authService.authenticate(request.getUsername(), request.getPassword(), ip);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Login failed");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return authService.isTokenValid(token) ? ResponseEntity.ok("Valid") : ResponseEntity.status(401).body("Invalid");
    }
}
