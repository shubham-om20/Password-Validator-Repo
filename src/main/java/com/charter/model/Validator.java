package com.charter.model;

import com.charter.customvalidator.PasswordValidatorConstraint;

import io.swagger.annotations.ApiModelProperty;

public class Validator {

	@PasswordValidatorConstraint
	 @ApiModelProperty(notes = "Password Constraint", example = "abc123")
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
