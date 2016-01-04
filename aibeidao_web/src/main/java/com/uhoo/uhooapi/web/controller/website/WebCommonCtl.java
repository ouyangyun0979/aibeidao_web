package com.uhoo.uhooapi.web.controller.website;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uhoo.uhooapi.web.controller.AbstractCtl;



@Controller
@RequestMapping(value="/web/common")
public class WebCommonCtl extends AbstractCtl{
	
	public final Logger logger = LoggerFactory.getLogger(WebCommonCtl.class);
	
	@RequestMapping(value="index",method={RequestMethod.GET,RequestMethod.POST})
	public String tomain(Model model) {
		return "website/index";
	} 
}