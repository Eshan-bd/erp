package com.brainstation23.erp.model.dto;

import com.brainstation23.erp.constant.ROLE;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@Setter
public class UserResponse {
	private UUID id;
	private ROLE role;
	private String firstName;
	private String lastName;
	private Integer balance;
}
