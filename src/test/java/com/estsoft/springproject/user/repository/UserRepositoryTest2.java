package com.estsoft.springproject.user.repository;

import com.estsoft.springproject.user.domain.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest2 {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 유저_이메일_체크() throws Exception{
        //given
        Users user = createUser();
        userRepository.save(user);

        //when
        Users returnUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("이메일 없음"));

        //then
        assertThat(returnUser.getPassword()).isEqualTo(user.getPassword());
        assertThat(returnUser.getEmail()).isEqualTo(user.getEmail());

    }

    @Test
    public void 회원_전체_조회() throws Exception{
        //given
        List<Users> users = Arrays.asList(
                new Users("t1","123"),
                new Users("t2","123"),
                new Users("t3","123"),
                new Users("t4","123")
        );
        userRepository.saveAll(users);

        //when
        List<Users> returnUsers = userRepository.findAll();
        returnUsers.forEach(user -> System.out.println(user.getEmail()));

        //then
        //빌드시 저장 되는 email 1개, 새로운 이메일 4개
        assertThat(returnUsers.size()).isEqualTo(5);

    }

    @Test
    public void 사용자_저장() throws Exception{
        //given
        Users user = createUser();
        //when
        Users savedUser = userRepository.save(user);
        //then
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());

    }

    //유저 생성
    private Users createUser(){
        String email = "test@test.com";
        String password = "pw";
        return new Users(email, password);
    }
}
