package com.brainstation23.erp.service;

import com.brainstation23.erp.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Slf4j
@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        var myUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Username " + userName + " not found."));


        var grantedAuthorities = Collections.unmodifiableList(
                AuthorityUtils.createAuthorityList(myUser.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(
                myUser.getFirstName(),
                myUser.getPassword(),
                grantedAuthorities
        );
    }

}