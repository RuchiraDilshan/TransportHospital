package com.example.TransportHospital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.TransportHospital.models.UserDetail;
import com.example.TransportHospital.repository.UserDetailRepo;

@Service
public class UserDetailService {

    private final UserDetailRepo userDetailRepository;

    public UserDetailService(UserDetailRepo userDetailRepo) {
        this.userDetailRepository = userDetailRepo;
    }

    public List<UserDetail> getAllUsers() {
        return userDetailRepository.findAll();
    }

    public Optional<UserDetail> getUserByUserid(Long userid) {
        return userDetailRepository.findByUserid(userid);
    }

    public UserDetail getUserByEmail(String useremail) {
        return userDetailRepository.findByUseremail(useremail).orElse(null);

    }

    public UserDetail saveUser(UserDetail user) {
        return userDetailRepository.save(user);
    }

    public void deleteUser(Long userid) {
        userDetailRepository.deleteById(userid);
    }

    public boolean useremailExists(String useremail) {
        return userDetailRepository.existsByUseremail(useremail);
    }

    public boolean userExists(String username) {
        return userDetailRepository.existsByUsername(username);

    }

}
