package com.brainstation23.erp.model.dto;

import com.brainstation23.erp.constant.ROLE;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
public class CreateUserRequest {
	@NotNull
	private ROLE role;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	private Integer balance;
}
