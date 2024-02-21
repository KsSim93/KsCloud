package com.ks.cloud.login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Login", description = "Login API Document")
@RestController
public class loginController {

    @GetMapping(value = {"/api/login"})
	public String getLogin() {
		log.info("get login");
		return "forward:/login";
	}
    @GetMapping(value = {"/api/logout"})
	public String getLogout() {
		log.info("get logout");
		return "forward:/login";
	}
}
