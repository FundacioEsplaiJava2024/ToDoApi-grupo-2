package com.grupo2.kanbanboard.services;

import org.springframework.stereotype.Service;

import com.grupo2.kanbanboard.entities.User;
import com.grupo2.kanbanboard.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(String email, String username, String password) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        return userRepository.save(user);
    }

}
