package com.charter.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordValidatorController {
	
	
	@GetMapping("/api/validate")
	public String validatePassword(@RequestHeader("authorization") String password) {
		
		return password;
	}

}
