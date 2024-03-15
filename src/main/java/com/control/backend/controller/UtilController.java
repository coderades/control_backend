package com.control.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.control.backend.util.PostalCodeUtil;

@RestController
@RequestMapping("/api/util")
public class UtilController {
	
	@GetMapping("/findByPostalCode/{postalCode}")
	public ResponseEntity<?> find(@PathVariable("postalCode") String postalCode) {
		return ResponseEntity.ok(PostalCodeUtil.findPostalCode(postalCode));
	}
}
