package com.brainstation23.erp.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class CreateOrganizationRequest {
	@NotNull
	@Schema(description = "Organization Name", example = "Brain Station 23")
	private String name;
}
