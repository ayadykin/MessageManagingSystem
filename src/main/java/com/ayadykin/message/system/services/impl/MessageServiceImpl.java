package com.ayadykin.message.system.services.impl;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ayadykin.message.system.dto.MessageDto;
import com.ayadykin.message.system.model.Message;
import com.ayadykin.message.system.model.User;
import com.ayadykin.message.system.services.MessageService;

import lombok.extern.java.Log;

@Log
@Service
@Qualifier("messageService")
public class MessageServiceImpl implements MessageService {

	private Map<String, User> users = new TreeMap<>();
	private long messageId;

	@PostConstruct
	public void init() {
		User user = new User("And");
		user.addMessage(new Message(++messageId, "ffffff"));
		user.addMessage(new Message(++messageId, "second"));
		users.put("And", user);
	}

	@Override
	public Map<String, User> getMessages() {
		log.info("getMessages" + users);
		return users;
	}

	@Override
	public boolean createMesage(MessageDto messageDto) {
		log.info("createMesage" + messageDto);
		User user = users.get(messageDto.getUserName());
		if (Objects.isNull(user)) {
			user = new User(messageDto.getUserName());
			users.put(user.getName(), user);
		}
		return user.addMessage(new Message(++messageId, messageDto.getMessage()));
	}

	@Override
	public boolean updateMesage(MessageDto messageDto) {
		log.info("updateMesage" + messageDto);
		User user = users.get(messageDto.getUserName());
		if (Objects.isNull(user)) {
			throw new RuntimeException("User not exist");
		}
		Message message = new Message(messageDto.getMessageId(), messageDto.getMessage());
		if (user.removeMessage(message)) {
			return user.addMessage(message);
		} else {
			throw new RuntimeException("Error update message. Message not exist");
		}
	}

	@Override
	public boolean deleteMesage(MessageDto messageDto) {
		log.info("deleteMesage" + messageDto);
		User user = users.get(messageDto.getUserName());
		if (Objects.isNull(user)) {
			throw new RuntimeException("User not exist");
		}
		if (!user.removeMessage(new Message(messageDto.getMessageId(), messageDto.getMessage()))) {
			throw new RuntimeException("Error delete message. Message not exist");
		} else {
			return true;
		}
	}

}
