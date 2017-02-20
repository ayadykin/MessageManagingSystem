package com.ayadykin.message.system.services.impl;

import java.util.Map;
import java.util.Objects;
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

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Map<String, User> getMessages() {
		log.info("getMessages");
		Map<String, User> usersDto = new TreeMap<>();
		for (User user : userDao.findAll()) {
			usersDto.put(user.getName(), user);
		}
		return usersDto;
	}

	@Override
	public boolean createMesage(MessageDto messageDto) {
		log.info("createMesage" + messageDto);
		User user = userDao.findByName(messageDto.getUserName());
		if (Objects.isNull(user)) {
			user = new User(messageDto.getUserName());
		}
		user.addMessage(new Message(user, messageDto.getMessage()));
		user = userDao.save(user);
		return true;
	}

	@Override
	public boolean updateMesage(MessageDto messageDto) {
		log.info("updateMesage" + messageDto);
		Message message = messageDao.findOne(messageDto.getMessageId());
		if (Objects.isNull(message)) {
			throw new RuntimeException("Message not exist");
		}
		message.setMessage(messageDto.getMessage());
		messageDao.save(message);
		return true;
	}

	@Override
	public boolean deleteMesage(MessageDto messageDto) {
		log.info("deleteMesage" + messageDto);
		messageDao.delete(messageDto.getMessageId());
		return true;
	}

}
