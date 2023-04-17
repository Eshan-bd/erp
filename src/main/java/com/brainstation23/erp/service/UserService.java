package com.brainstation23.erp.service;

import com.brainstation23.erp.constant.ROLE;
import com.brainstation23.erp.exception.custom.custom.NotFoundException;
import com.brainstation23.erp.mapper.UserMapper;
import com.brainstation23.erp.model.domain.MyUser;
import com.brainstation23.erp.model.dto.CreateUserRequest;
import com.brainstation23.erp.model.dto.UpdateUserRequest;
import com.brainstation23.erp.persistence.entity.UserEntity;
import com.brainstation23.erp.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService{
	public static final String USER_NOT_FOUND = "User Not Found";
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public Page<MyUser> getAll(Pageable pageable) {
		var entities = userRepository.findAll(pageable);
		return entities.map(userMapper::entityToDomain);
	}

	public Page<MyUser> getAllByRole(Pageable pageable, ROLE role) {
		var entities = userRepository.findAllByRole(pageable, role);
		return entities.map(userMapper::entityToDomain);
	}

	public MyUser getOne(UUID id) {
		var entity = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
		return userMapper.entityToDomain(entity);
	}

	public UUID createOne(CreateUserRequest createRequest) {
		var entity = new UserEntity();
		entity.setId(UUID.randomUUID())
				.setRole(createRequest.getRole())
				.setUserName(createRequest.getUserName())
				.setFirstName(createRequest.getFirstName())
				.setLastName(createRequest.getLastName())
				.setBalance(createRequest.getBalance());
		var createdEntity = userRepository.save(entity);
		return createdEntity.getId();
	}

	public void updateOne(UUID id, UpdateUserRequest updateRequest) {
		var entity = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

		entity.setRole(updateRequest.getRole())
				.setFirstName(updateRequest.getFirstName())
				.setLastName((updateRequest.getLastName()))
				.setBalance(updateRequest.getBalance());

		userRepository.save(entity);
	}

	public void deleteOne(UUID id) {
		userRepository.deleteById(id);
	}

	public Integer getBalanceById(UUID id){
		var myUser = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(id));
		return myUser.getBalance();

	}

}