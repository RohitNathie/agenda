package com.example.demo.repository;

import com.example.demo.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepo extends JpaRepository<UserCredentials, Long>{

    UserCredentials findByUsername(String username);
    Optional<UserCredentials> findByUsernameAndPassword(String username, String password);
}
