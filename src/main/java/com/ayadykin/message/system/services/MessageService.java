package com.ayadykin.message.system.services;

import java.util.Map;

import com.ayadykin.message.system.dto.MessageDto;
import com.ayadykin.message.system.model.User;

public interface MessageService {

	public Map<String, User> getMessages();

	public Map<String, User> createMesage(MessageDto message);

	public Map<String, User> updateMesage(MessageDto message);

	public Map<String, User> deleteMesage(MessageDto message);
}
