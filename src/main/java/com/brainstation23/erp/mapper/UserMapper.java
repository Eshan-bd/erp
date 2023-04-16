package com.brainstation23.erp.mapper;

import com.brainstation23.erp.model.domain.MyUser;
import com.brainstation23.erp.model.dto.UserResponse;
import com.brainstation23.erp.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
	MyUser entityToDomain(UserEntity entity);
	UserResponse domainToResponse(MyUser myUser);
}
