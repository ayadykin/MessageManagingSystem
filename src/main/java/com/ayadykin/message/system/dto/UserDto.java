package com.ayadykin.message.system.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	@NotEmpty
	private long userId;
	@NotEmpty
	private String userName;
}
