package com.brainstation23.erp.service;

import com.brainstation23.erp.exception.custom.custom.IncorrectEmailOrPassword;
import com.brainstation23.erp.persistence.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;


@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        log.info("looking for user");

        var myUser = userRepository.findByFirstName(firstName)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found."));

        log.info("found user for auth.");

        var grantedAuthorities = Collections.unmodifiableList(
                AuthorityUtils.createAuthorityList(myUser.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(
                myUser.getFirstName(),
                myUser.getPassword(),
                grantedAuthorities
        );
    }

    public boolean isValidEmail(String emailAddress) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

}