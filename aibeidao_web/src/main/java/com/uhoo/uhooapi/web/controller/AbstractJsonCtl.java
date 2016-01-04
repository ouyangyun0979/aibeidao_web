package com.uhoo.uhooapi.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uhoo.uhooapi.app.api.dto.OpResult;


public class AbstractJsonCtl {
	
	public final Logger logger = LoggerFactory.getLogger(AbstractJsonCtl.class);
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody OpResult handleException(Exception e, HttpServletResponse response) {
		OpResult r = new OpResult();
		r.setIsOk(false);
		r.setOpErrorMsg(e.getMessage());
		logger.debug("Json controller error:",e);
	    return r;
	}

}
