package com.brainstation23.erp.persistence;

import com.brainstation23.erp.constant.ROLE;
import com.brainstation23.erp.persistence.entity.OrganizationEntity;
import com.brainstation23.erp.persistence.entity.UserEntity;
import com.brainstation23.erp.persistence.repository.OrganizationRepository;
import com.brainstation23.erp.persistence.repository.UserRepository;
import com.brainstation23.erp.util.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import java.util.UUID;

@Slf4j
@Configuration
class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, OrganizationRepository organizationRepository) {

        return args -> {
            // Organizations
            log.info("Preloading " + organizationRepository.save(new OrganizationEntity(
                    UUID.randomUUID(),
                    "Gazi Software",
                    RandomUtils.generateAlphaNumeric(6).toUpperCase()
            )));
            log.info("Preloading " + organizationRepository.save(new OrganizationEntity(
                    UUID.randomUUID(),
                    "Kazi Hardware",
                    RandomUtils.generateAlphaNumeric(6).toUpperCase()
                    )));
            log.info("Preloading " + organizationRepository.save(new OrganizationEntity(
                    UUID.randomUUID(),
                    "Walton Television",
                    RandomUtils.generateAlphaNumeric(6).toUpperCase()
            )));
            log.info("Preloading " + organizationRepository.save(new OrganizationEntity(
                    UUID.randomUUID(),
                    "RFL Furniture",
                    RandomUtils.generateAlphaNumeric(6).toUpperCase()
            )));

            // Users
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
                    "habib",
                    ROLE.EMPLOYEE,
                    "Habib",
                    "Hasan",
                    700,
                    PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("abcd")
//                    "{bcrypt}" + new BCryptPasswordEncoder().encode("1234")
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