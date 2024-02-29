package com.ks.cloud.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ks.cloud.users.dto.UserDTO;
import com.ks.cloud.users.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "Login", description = "Login API Document")
@RestController
public class loginController {

	@Autowired
	private UserService userService;

    @PostMapping(value = {"/api/login"})
	public ResponseEntity<Object> getLogin(@RequestBody UserDTO userDTO) {
		log.info("login user {} ",userDTO.getUserId());
		if (userService.login(userDTO)) {
			return ResponseEntity.ok(userService.getUserInfo(userDTO.getUserId()));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
	}

	@GetMapping(value = "/api/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
	}

    @GetMapping(value = {"/api/logout"})
	public String getLogout() {
		log.info("get logout");
		return "forward:/login";
	}
}
