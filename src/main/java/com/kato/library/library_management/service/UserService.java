package com.kato.library.library_management.service;

import org.springframework.stereotype.Service;
import com.kato.library.library_management.entity.User;
import com.kato.library.library_management.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy user"));
    }
}
