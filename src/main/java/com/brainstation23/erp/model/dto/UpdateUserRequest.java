package com.brainstation23.erp.model.dto;

import com.brainstation23.erp.constant.ROLE;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
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
	private String password;
}
