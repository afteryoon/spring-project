package com.estsoft.springproject.user.repository;

import com.estsoft.springproject.user.domain.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    // select * from users where email = ${email};
    Optional<Users> findByEmail(String email);

}
