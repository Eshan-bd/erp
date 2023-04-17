package com.brainstation23.erp.persistence;

import com.brainstation23.erp.constant.ROLE;
import com.brainstation23.erp.persistence.entity.UserEntity;
import com.brainstation23.erp.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {

        return args -> {
            log.info("Preloading " + userRepository.save(new UserEntity(
                    UUID.randomUUID(),
                    ROLE.EMPLOYEE,
                    "Karim",
                    "Rahman",
                    400,
                    new BCryptPasswordEncoder().encode("1234")
            )));

            log.info("Preloading " + userRepository.save(new UserEntity(
                    UUID.randomUUID(),
                    ROLE.ADMIN,
                    "Rahim",
                    "Uddin",
                    700,
                    new BCryptPasswordEncoder().encode("1234")
            )));
        };
    }
}