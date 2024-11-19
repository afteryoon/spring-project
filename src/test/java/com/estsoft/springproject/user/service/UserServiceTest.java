package com.estsoft.springproject.user.service;

import com.estsoft.springproject.user.domain.Users;
import com.estsoft.springproject.user.domain.dto.AddUserRequest;
import com.estsoft.springproject.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void 회원_가입_테스트() throws Exception{
        //given
        String email ="mock_email";
        String rawPassword = "mock_password";
        AddUserRequest request = new AddUserRequest();
        request.setEmail(email);
        request.setPassword(bCryptPasswordEncoder.encode(rawPassword));

        Mockito.when(userRepository.save(any())).thenReturn(new Users(request.getEmail(), request.getPassword()));
        //when
        Users addUsers = userService.save(request);
        //then
        assertThat(addUsers).isNotNull();
        assertThat(addUsers.getEmail()).isEqualTo(email);
        assertThat(bCryptPasswordEncoder.matches(rawPassword, addUsers.getPassword())).isTrue();

        verify(userRepository, times(1)).save(any(Users.class));
        verify(bCryptPasswordEncoder, times(2)).encode(any(String.class));


    }
}