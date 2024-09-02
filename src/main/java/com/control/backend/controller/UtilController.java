package com.control.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.util.MailUtil;

@RestController
@RequestMapping("/api/util")
public class UtilController {
	
	@GetMapping("/mail/findbypostalcode/{postalcode}")
	public ResponseEntity<?> find(@PathVariable("postalcode") String postalCode) {
		return ResponseEntity.ok(MailUtil.findPostalCode(postalCode));
	}
}
