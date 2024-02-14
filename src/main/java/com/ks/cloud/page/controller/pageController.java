package com.ks.cloud.page.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class pageController {

	@GetMapping("/")
	public ModelAndView mainPage() {
		ModelAndView modelAndView = new ModelAndView("index.html");
		log.info(modelAndView.getViewName());
		return modelAndView;
	}
	
	@GetMapping("/error")
	public ModelAndView getErrorPage() {
		ModelAndView modelAndView = new ModelAndView("error");
		log.info(modelAndView.getViewName());
		return modelAndView;
	}
	@GetMapping("/login")
	public ModelAndView getLoginPage() {
		ModelAndView modelAndView = new ModelAndView("login");
		log.info(modelAndView.getViewName());
		return modelAndView;
	}
}
