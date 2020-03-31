package com.charter.exception;

import java.util.List;


public class ValidationErrorResponse {
	
	private  List<String> violations;
	 
	 
	public ValidationErrorResponse( List<String> violations) {
		super();
		this.violations = violations;
	}

	public List<String> getViolations() {
		return violations;
	}

	public void setViolations(List<String> violations) {
		this.violations = violations;
	}


	
	

}
