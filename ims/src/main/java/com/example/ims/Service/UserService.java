package com.example.ims.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ims.Model.User;
import com.example.ims.Repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean validateUser(String username, String password) {
        List<User> matchedUsers = userRepository.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .collect(Collectors.toList());

        return !matchedUsers.isEmpty();
    }

    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }
    
    public void createUser(User user) {
        userRepository.save(user);
    }
}
