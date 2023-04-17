package com.brainstation23.erp.persistence.entity;

import com.brainstation23.erp.constant.EntityConstant;
import com.brainstation23.erp.constant.ROLE;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = EntityConstant.USER)
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	@Id
	@Type(type = "uuid-char")
	private UUID id;
	private String userName;
	private ROLE role;
	private String firstName;
	private String lastName;
	private Integer balance;
	private String password;
}
