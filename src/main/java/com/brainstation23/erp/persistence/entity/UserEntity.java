package com.brainstation23.erp.persistence.entity;

import com.brainstation23.erp.constant.EntityConstant;
import com.brainstation23.erp.constant.ROLE;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Entity
@Table(name = EntityConstant.USER)
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends Auditable<String>{
	@Id
	private UUID id;
	private String userName;
	private ROLE role;
	private String firstName;
	private String lastName;
	private Integer balance;
	private String password;
}
