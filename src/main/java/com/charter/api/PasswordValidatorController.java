package com.charter.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.charter.model.Message;
import com.charter.model.Validator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Password Validator", value = "PasswordValidator")
public class PasswordValidatorController {
	
	@Autowired
	Message message;
	
	@PostMapping(value = "/api/validate",  produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Validate Password")
	public Message validatePassword(@RequestBody @Valid Validator validator) {
		
		message.setMessage("Password looks good");
		
		return message;
	}

}
