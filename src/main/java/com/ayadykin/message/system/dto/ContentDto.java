package com.ayadykin.message.system.dto;

import com.ayadykin.message.system.model.Message;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContentDto {

	private String userName;
	private Message message;

	public ContentDto(String userName, Message message) {
		this.userName = userName;
		this.message = message;
	}

}
