package com.brainstation23.erp.service;

import com.brainstation23.erp.constant.ROLE;
import com.brainstation23.erp.exception.custom.custom.NotFoundException;
import com.brainstation23.erp.model.dto.CreateUserRequest;
import com.brainstation23.erp.model.dto.UpdateUserRequest;
import com.brainstation23.erp.persistence.entity.UserEntity;
import com.brainstation23.erp.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    private final UserService userService;
    private final UserRepository userRepository;

    public static final String USER_NOT_FOUND = "User Not Found";
    public static final String USER_ALREADY_EXISTS= "User Already Exists";

    @Autowired
    public UserServiceTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Test
    public void getAllTest(){
        userRepository.save(new UserEntity(
                UUID.randomUUID(),
                "karim",
                ROLE.EMPLOYEE,
                "Karim",
                "Rahman",
                400,
                "{bcrypt}$2a$10$h/AJueu7Xt9yh3qYuAXtk.WZJ544Uc2kdOKlHu2qQzCh/A3rq46qm"
        ));
        userRepository.save(new UserEntity(
                UUID.randomUUID(),
                "rahim",
                ROLE.ADMIN,
                "Rahim",
                "Uddin",
                700,
                PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234")
        ));
        assertNotNull(userService.getAll(PageRequest.of(0, 20)));
    }

    @Test
    public void createOneTest(){
        var createRequest = new CreateUserRequest(
                "abul",
                ROLE.EMPLOYEE,
                "Abul",
                "Hasan",
                78,
                 "test"
        );
        var user = userRepository.findById(userService.createOne(createRequest))
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        assertEquals(createRequest.getUserName(), user.getUserName());
        assertEquals(createRequest.getRole(), user.getRole());
        assertEquals(createRequest.getFirstName(), user.getFirstName());
        assertEquals(createRequest.getLastName(), user.getLastName());
        assertEquals(createRequest.getBalance(), user.getBalance());
        assertTrue(
                PasswordEncoderFactories.createDelegatingPasswordEncoder()
                        .matches(createRequest.getPassword(), user.getPassword()));
    }

    @Test
    public void updateTest(){
        var id = UUID.randomUUID();
        userRepository.save(new UserEntity(
                id,
                "karim",
                ROLE.EMPLOYEE,
                "Karim",
                "Rahman",
                400,
                "{bcrypt}$2a$10$h/AJueu7Xt9yh3qYuAXtk.WZJ544Uc2kdOKlHu2qQzCh/A3rq46qm"
        ));
        var updateRequest = new UpdateUserRequest(
                ROLE.EMPLOYEE,
                "Jorina",
                "Begum",
                64,
                "updateTest"
        );
        userService.updateOne(id, updateRequest);
        var user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_ALREADY_EXISTS));

        assertEquals(updateRequest.getRole(), user.getRole());
        assertEquals(updateRequest.getFirstName(), user.getFirstName());
        assertEquals(updateRequest.getLastName(), user.getLastName());
        assertEquals(updateRequest.getBalance(), user.getBalance());
        assertTrue(
                PasswordEncoderFactories.createDelegatingPasswordEncoder()
                        .matches(updateRequest.getPassword(), user.getPassword()));
    }

    @Test
    public void deleteTest(){
        var id = UUID.randomUUID();
        userRepository.save(new UserEntity(
                id,
                "karim",
                ROLE.EMPLOYEE,
                "Karim",
                "Rahman",
                400,
                "{bcrypt}$2a$10$h/AJueu7Xt9yh3qYuAXtk.WZJ544Uc2kdOKlHu2qQzCh/A3rq46qm"
        ));
        userService.deleteOne(id);
        assertFalse(userRepository.existsById(id));
    }
}
