package com.example.application.service;

import com.example.application.domain.User;
import com.example.application.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserByUsername(String username){
        return userRepo.findByUsername(username);
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public void deleteUserByUsername(String username){
        userRepo.delete(userRepo.findByUsername(username));
    }
    public void save(User user) {
        userRepo.save(user);
    }
}
