package com.brainstation23.erp.model.dto;

import com.brainstation23.erp.constant.ROLE;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class CreateUserRequest {
	@NotNull
	private String userName;
	@NotNull
	private ROLE role;
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	private Integer balance;
	private String password;
}
