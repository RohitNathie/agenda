package com.example.demo.service;

import com.example.demo.model.UserCredentials;
import com.example.demo.repository.UserDataRepo;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UserDataRepo userDataRepo;

    public UsersService(UserDataRepo userDataRepo) {
        this.userDataRepo = userDataRepo;
    }

    public UserCredentials registerUser(String username, String password) {
        if(username == null && password == null) {
            return null;
        }else {
            UserCredentials existingUser = userDataRepo.findByUsername(username);
            if (existingUser != null) {
                return null; // User with the same username already exists
            } else {
                UserCredentials userCredentials = new UserCredentials(username, password);
                return userDataRepo.save(userCredentials);
            }
        }
    }

    public UserCredentials authenticate(String username, String password) {
        return userDataRepo.findByUsernameAndPassword(username, password).orElse(null);
    }

}
