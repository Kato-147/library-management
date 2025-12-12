package com.kato.library.library_management.service;

import com.kato.library.library_management.entity.User;
import com.kato.library.library_management.repository.UserRepository;
import com.kato.library.library_management.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    //System.out.println("DEBUG: " + userRepository.findByEmail("admin@gmail.com"));


    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email đã tồn tại.");
        }

        // gán role mặc định nếu client không gửi lên
        if (user.getRole() == null) {
            user.setRole(User.Role.USER);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }


    public String login(String email, String rawPassword) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Email không tồn tại"));

            if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
                throw new RuntimeException("Sai mật khẩu");
            }

            return jwtUtil.generateToken(user.getEmail(),user.getRole().name());

        } catch (RuntimeException e) {
            throw new RuntimeException("Đăng nhập thất bại: " + e.getMessage());
        }
    }
}
