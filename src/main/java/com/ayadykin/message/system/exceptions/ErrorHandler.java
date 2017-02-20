package com.ayadykin.message.system.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;

@Log
@ControllerAdvice
public class ErrorHandler {

	@ResponseBody
	@ExceptionHandler({ RuntimeException.class })
	public ModelAndView error(Exception e) {
		log.warning("error :" + e.getMessage());
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("error", e.getMessage());
		return mav;
	}
}
