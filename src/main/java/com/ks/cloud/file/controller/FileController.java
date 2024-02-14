package com.ks.cloud.file.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Tag(name = "File", description = "file API Document")
@RestController
public class FileController {

	@GetMapping("/list")
	public String getFileList() {
		log.info("into get File List");
		return "";
	}
}
