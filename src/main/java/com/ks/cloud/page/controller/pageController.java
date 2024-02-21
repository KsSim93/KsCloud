package com.ks.cloud.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class pageController {

	@RequestMapping(value = {"/","/kscloud/**"})
	public String viewMapping() {
		log.info("get paging");
		return "forward:/index.html";
	}
	@RequestMapping(value = {"/login"})
	public String goLogin() {
		log.info("get login");
		return "forward:/index.html";
	}

}
