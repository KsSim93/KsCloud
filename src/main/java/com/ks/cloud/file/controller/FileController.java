package com.ks.cloud.file.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "File", description = "file API Document")
@RestController
public class FileController {

	@GetMapping("/list")
	public String getFileList() {
		
		return "";
	}
}
