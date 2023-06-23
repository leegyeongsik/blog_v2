package com.blog.blog.Service;

import com.blog.blog.Repository.UserRepository;
import com.blog.blog.dto.LoginRequestDto;
import com.blog.blog.dto.SignUpRequestDto;
import com.blog.blog.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public String signup(SignUpRequestDto signUpRequestDto) {
        String username = signUpRequestDto.getUsername();
        String password = passwordEncoder.encode(signUpRequestDto.getPassword());
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        User user = new User(username, password);
        userRepository.save(user);
        return "회원가입 성공";

    }

    public String login(LoginRequestDto requestDto) {
        return null;

    }
}
