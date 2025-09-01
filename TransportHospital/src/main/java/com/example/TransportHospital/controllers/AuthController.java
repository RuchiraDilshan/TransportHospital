package com.example.TransportHospital.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TransportHospital.dto.LoginRequest;
import com.example.TransportHospital.models.UserDetail;
import com.example.TransportHospital.service.SessionService;
import com.example.TransportHospital.service.UserDetailService;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserDetailService userDetailService;

    private final SessionService sessionService;

    public AuthController(UserDetailService userDetailService, SessionService sessionService) {
        this.userDetailService = userDetailService;

        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetail> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        UserDetail user = userDetailService.getUserByEmail(loginRequest.getUseremail());

        if (user != null && user.getUserpassword().equals(loginRequest.getUserpassword())) {
            sessionService.loginUser(session, user);
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(401).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        sessionService.logoutUser(session);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserDetail> getCurrentUser(HttpSession session) {
        UserDetail user = sessionService.getCurrentUser(session);
        if (user != null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(user);
    }

}
