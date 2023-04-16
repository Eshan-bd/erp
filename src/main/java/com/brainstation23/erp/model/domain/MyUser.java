package com.brainstation23.erp.model.domain;

import com.brainstation23.erp.constant.ROLE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyUser {
	private UUID id;
	private ROLE role;
	private String firstName;
	private String lastName;
	private Integer balance;
}
