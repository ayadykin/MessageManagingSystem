package com.ayadykin.message.system.dto;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDto {

	private long userId;
	
	@NotEmpty(message = "Empty user name")
	private String userName;

	private long messageId;
	
	@NotEmpty(message = "Empty message")
	private String message;

	public MessageDto(String userName, long messageId, String message) {
		this.userName = userName;
		this.messageId = messageId;
		this.message = message;
	}

}
