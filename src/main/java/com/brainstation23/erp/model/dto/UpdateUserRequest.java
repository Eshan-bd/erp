package com.brainstation23.erp.model.dto;

import com.brainstation23.erp.constant.ROLE;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@ToString
@Getter
@Setter
public class UpdateUserRequest {
	private ROLE role;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	private Integer balance;
}
