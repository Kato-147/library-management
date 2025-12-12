package com.kato.library.library_management;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class passwordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("123");
        System.out.println(hash);
    }
}
