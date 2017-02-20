package com.ayadykin.message.system.services;

import java.util.Map;

import com.ayadykin.message.system.dto.MessageDto;
import com.ayadykin.message.system.model.User;

public interface MessageService {

	public Map<String, User> getMessages();

	public boolean createMesage(MessageDto message);

	public boolean updateMesage(MessageDto message);

	public boolean deleteMesage(MessageDto message);
}
