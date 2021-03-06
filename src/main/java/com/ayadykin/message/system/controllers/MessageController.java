package com.ayadykin.message.system.controllers;

import static com.ayadykin.message.system.utils.ApplicationMapping.CREATE_MESSAGE;
import static com.ayadykin.message.system.utils.ApplicationMapping.DELETE_MESSAGE;
import static com.ayadykin.message.system.utils.ApplicationMapping.UPDATE_MESSAGE;

import java.util.Map;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ayadykin.message.system.dto.MessageDto;
import com.ayadykin.message.system.services.MessageService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/message")
public class MessageController {

	private final static String CONTENT = "content";
	private final static String MESSAGE = "message";
	private final static String MESSAGE_VIEW = "message";
	private final static String CREATE_EDIT_VIEW = "create_edit";

	@Autowired
	@Qualifier("messageService")
	private MessageService messageServiceCol;

	@Autowired
	@Qualifier("messageServiceDb")
	private MessageService messageServiceDb;

	@GetMapping
	public String getMessages(@PathParam(value = "impl") String impl, Map<String, Object> model) {
		log.info("getMessages impl : " + impl);
		MessageService messageService = getImplementation(impl);
		model.put(CONTENT, messageService.getMessages());
		model.put("impl", impl);
		return MESSAGE_VIEW;
	}

	@GetMapping(CREATE_MESSAGE)
	public String createView(@PathParam(value = "impl") String impl, Map<String, Object> model) {
		log.info("createView impl : " + impl);
		model.put(MESSAGE, new MessageDto());
		model.put("action", "/message/create?impl=" + impl);
		return CREATE_EDIT_VIEW;
	}

	@PostMapping(CREATE_MESSAGE)
	public String createMessages(@Valid @ModelAttribute("message") MessageDto messageDto, BindingResult result,
			@PathParam(value = "impl") String impl, Map<String, Object> model) {
		log.info("createMessages impl : " + impl);
		if (result.hasErrors()) {
			log.warning("Error validate messageDto : " + messageDto);
			return CREATE_EDIT_VIEW;
		}
		MessageService messageService = getImplementation(impl);
		messageService.createMesage(messageDto);
		return "redirect:/" + MESSAGE_VIEW + "?impl=" + impl;
	}

	@GetMapping(UPDATE_MESSAGE)
	public String updateView(@Valid @ModelAttribute MessageDto messageDto, Map<String, Object> model,
			@PathParam(value = "impl") String impl) {
		log.info("updateView impl : " + impl);
		model.put(MESSAGE, messageDto);
		model.put("action", "/message/update?impl=" + impl);
		return CREATE_EDIT_VIEW;
	}

	@PostMapping(UPDATE_MESSAGE)
	public String updeteMessages(@Valid @ModelAttribute("message") MessageDto messageDto, BindingResult result, Map<String, Object> model,
			@PathParam(value = "impl") String impl) {
		log.info("updeteMessages impl : " + impl);
		if (result.hasErrors()) {
			log.warning("Error validate messageDto : " + messageDto);
			model.put(MESSAGE, new MessageDto());
			return CREATE_EDIT_VIEW;
		}
		MessageService messageService = getImplementation(impl);
		messageService.updateMesage(messageDto);
		return "redirect:/" + MESSAGE_VIEW + "?impl=" + impl;
	}

	@GetMapping(DELETE_MESSAGE)
	public String deleteMessages(@Valid @ModelAttribute MessageDto messageDto, Map<String, Object> model,
			@PathParam(value = "impl") String impl) {
		log.info("deleteMessages impl : " + impl);
		MessageService messageService = getImplementation(impl);
		messageService.deleteMesage(messageDto);
		return "redirect:/" + MESSAGE_VIEW + "?impl=" + impl;
	}

	private MessageService getImplementation(String impl) {
		if ("DB".equals(impl)) {
			return messageServiceDb;
		} else {
			return messageServiceCol;
		}
	}
}
