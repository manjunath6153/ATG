package com.atg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLoginPage() {
		return new ModelAndView("index");
	}
}
