package com.brainstation23.erp.persistence;

import com.brainstation23.erp.constant.ROLE;
import com.brainstation23.erp.persistence.entity.UserEntity;
import com.brainstation23.erp.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Slf4j
@Configuration
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {

        return args -> {
            log.info("Preloading " + userRepository.save(new UserEntity(
                    UUID.randomUUID(),
                    "karim",
                    ROLE.EMPLOYEE,
                    "Karim",
                    "Rahman",
                    400,
                    "{bcrypt}$2a$10$h/AJueu7Xt9yh3qYuAXtk.WZJ544Uc2kdOKlHu2qQzCh/A3rq46qm"
//                    "{bcrypt}" + new BCryptPasswordEncoder().encode("pass")
            )));

            log.info("Preloading " + userRepository.save(new UserEntity(
                    UUID.randomUUID(),
                    "rahim",
                    ROLE.ADMIN,
                    "Rahim",
                    "Uddin",
                    700,
                    PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234")
//                    "{bcrypt}" + new BCryptPasswordEncoder().encode("1234")
            )));
        };
    }
}