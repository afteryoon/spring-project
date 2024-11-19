package com.estsoft.springproject.user.domain.dto;

import com.estsoft.springproject.user.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter @Setter
@NoArgsConstructor
public class AddUserRequest {
    private String email;
    private String password;


    //pw
    //PW Encoder 및 회원 가입 로직
    public Users toUsers(BCryptPasswordEncoder encoder ) {
        String encodedPassword = encoder.encode(this.getPassword());
        return new Users(email, encodedPassword);
    }


}
