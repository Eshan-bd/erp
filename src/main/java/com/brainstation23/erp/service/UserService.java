package com.brainstation23.erp.service;

import com.brainstation23.erp.constant.ROLE;
import com.brainstation23.erp.exception.custom.custom.AlreadyExistsException;
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
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService{
	public static final String USER_NOT_FOUND = "User Not Found";
	public static final String USER_ALREADY_EXISTS= "User Already Exists";
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
		if (userRepository.existsByUserName(createRequest.getUserName())){
			throw new AlreadyExistsException(USER_ALREADY_EXISTS);
		}

		var entity = new UserEntity();
		entity.setId(UUID.randomUUID())
				.setRole(createRequest.getRole())
				.setUserName(createRequest.getUserName())
				.setFirstName(createRequest.getFirstName())
				.setLastName(createRequest.getLastName())
				.setBalance(createRequest.getBalance())
				.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(createRequest.getPassword()));
		var createdEntity = userRepository.save(entity);
		return createdEntity.getId();
	}

	public void updateOne(UUID id, UpdateUserRequest updateRequest) {
		var entity = userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

		entity.setRole(updateRequest.getRole())
				.setFirstName(updateRequest.getFirstName())
				.setLastName((updateRequest.getLastName()))
				.setBalance(updateRequest.getBalance())
				.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(updateRequest.getPassword()));

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

	public MyUser getOneByUserName(String userName){
		var entity = userRepository.findByUserName(userName)
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

		return userMapper.entityToDomain(entity);
	}

	public void updateOne(String name, UpdateUserRequest updateRequest) {
		var entity = userRepository.findByUserName(name)
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

		entity.setFirstName(updateRequest.getFirstName())
				.setLastName((updateRequest.getLastName()))
				.setPassword(updateRequest.getPassword());

		userRepository.save(entity);
	}
}