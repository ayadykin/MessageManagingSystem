package com.ayadykin.message.system.services.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ayadykin.message.system.dao.MessageDao;
import com.ayadykin.message.system.dao.UserDao;
import com.ayadykin.message.system.dto.MessageDto;
import com.ayadykin.message.system.model.Message;
import com.ayadykin.message.system.model.User;
import com.ayadykin.message.system.services.MessageService;

import lombok.extern.java.Log;

@Log
@Service
@Qualifier("messageServiceDb")
public class MessageServiceDbImpl implements MessageService {

	private Map<String, User> usersDto = new TreeMap<>();

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private UserDao userDao;

	private Map<String, User> covert(Iterable<User> users) {

		for (User user : users) {
			usersDto.put(user.getName(), user);
		}
		log.info("covert" + usersDto);
		return usersDto;

	}

	@Override
	public Map<String, User> getMessages() {
		log.info("getMessages");
		return covert(userDao.findAll());
	}

	@Override // userId //message
	public Map<String, User> createMesage(MessageDto messageDto) {
		log.info("createMesage" + messageDto);
		User user = userDao.findByName(messageDto.getUserName());
		if (Objects.isNull(user)) {
			user = new User(messageDto.getUserName());
		}

		user.addMessage(new Message(user, messageDto.getMessage()));
		user = userDao.save(user);

		return usersDto;
	}

	@Override // userId //message //messageId
	public Map<String, User> updateMesage(MessageDto messageDto) {
		log.info("updateMesage" + messageDto);
		User user = userDao.findByName(messageDto.getUserName());
		if (Objects.isNull(user)) {
			throw new RuntimeException("User not exist");
		}
		Message message = messageDao.findOne(messageDto.getMessageId());
		message.setMessage(messageDto.getMessage());
		messageDao.save(message);
		return usersDto;
	}

	@Override
	public Map<String, User> deleteMesage(MessageDto message) {
		messageDao.delete(message.getMessageId());
		return usersDto;
	}

}
