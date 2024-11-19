package org.example.postproject.util;

import lombok.RequiredArgsConstructor;
import org.example.postproject.entities.User;
import org.example.postproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
/**
 * progremma ishga tushgan vaqtda yangi user yaratib oladi
 */
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")) {
            Optional<User> byUsername = userRepository.findByUsername("admin");
            if (byUsername.isEmpty()) {
                User user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("admin"));
                userRepository.save(user);
            }
        }
    }
}
