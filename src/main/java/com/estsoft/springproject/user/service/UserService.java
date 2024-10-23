package com.estsoft.springproject.user.service;

import com.estsoft.springproject.user.domain.Users;
import com.estsoft.springproject.user.domain.dto.AddUserRequest;
import com.estsoft.springproject.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    //PW Encoder
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


//    // 회원가입 처리 (비즈니스 로직)
//    public Users save(AddUserRequest dto) {
//        return userRepository.save(dto.toUsers(encoder));
//    }

    // 회원가입 처리 (비즈니스 로직)
    public Users save(AddUserRequest dto) {
        String password = dto.getPassword();
        String email = dto.getEmail();
        String encodedPassword = encoder.encode(password);
        Users users = new Users(email, encodedPassword);
        return userRepository.save(users);
    }

}
