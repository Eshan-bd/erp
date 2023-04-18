package com.brainstation23.erp.service;

import com.brainstation23.erp.constant.ROLE;
import com.brainstation23.erp.persistence.entity.UserEntity;
import com.brainstation23.erp.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class MyUserDetailsServiceTest {
    private final MyUserDetailsService myUserDetailsService;
    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsServiceTest(MyUserDetailsService myUserDetailsService, UserRepository userRepository) {
        this.myUserDetailsService = myUserDetailsService;
        this.userRepository = userRepository;
    }

    @Test
    public void loadUserByUserNameTest(){
        var userName = "sokina";
        var password = "{bcrypt}$2a$10$h/AJueu7Xt9yh3qYuAXtk.WZJ544Uc2kdOKlHu2qQzCh/A3rq46qm";
        userRepository.save(new UserEntity(
                UUID.randomUUID(),
                userName,
                ROLE.EMPLOYEE,
                "Sokina",
                "Khatun",
                400,
                password
        ));
        assertEquals(password,
                myUserDetailsService.loadUserByUsername(userName).getPassword());
    }
}
