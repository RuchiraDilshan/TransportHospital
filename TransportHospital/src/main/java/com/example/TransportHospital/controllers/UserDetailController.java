package com.example.TransportHospital.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TransportHospital.models.UserDetail;
import com.example.TransportHospital.service.UserDetailService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserDetailController {

    private final UserDetailService userDetailService;

    public UserDetailController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping
    public List<UserDetail> getAllUsers() {
        return userDetailService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetail> getUserByUserid(@PathVariable Long userid) {
        Optional<UserDetail> user = userDetailService.getUserByUserid(userid);

        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/email/{useremail}")
    public ResponseEntity<UserDetail> getUserByEmail(@PathVariable String useremail) {
        UserDetail user = userDetailService.getUserByEmail(useremail);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public UserDetail createUser(@RequestBody UserDetail user) {

        return userDetailService.saveUser(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userid) {
        if (userDetailService.getUserByUserid(userid).isEmpty()) {
            return ResponseEntity.notFound().build();

        }

        userDetailService.deleteUser(userid);
        return ResponseEntity.noContent().build();
    }

}
