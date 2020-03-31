package com.charter;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.charter.model.Validator;

public class PasswordValidatorTest {

	private static LocalValidatorFactoryBean localValidatorFactory;
	private Validator validatorModel;

	@BeforeAll
	public static void setup() {

		localValidatorFactory = new LocalValidatorFactoryBean();
		localValidatorFactory.setProviderClass(HibernateValidator.class);
		localValidatorFactory.afterPropertiesSet();

	}

	@BeforeEach
	public void setTest() {

		validatorModel = new Validator();
	}

	@Test
	public void givenPassword_whenUpperCase_thenDisplayOneErrorMessage() {

		final String testValue = "abc123AA";
		validatorModel.setPassword(testValue);
		Set<ConstraintViolation<Validator>> constraintViolations = localValidatorFactory.validate(validatorModel);
		Assertions.assertEquals(true, constraintViolations.size() == 1);
	}

	@Test
	public void givenPassword_whenLowercaseOnly_andSizeSmallerThen5_thenDisplayTwoErrorMessage() {

		final String testValue = "abc";
		validatorModel.setPassword(testValue);
		Set<ConstraintViolation<Validator>> constraintViolations = localValidatorFactory.validate(validatorModel);
		Assertions.assertEquals(true, constraintViolations.size() == 2);
	}

	@Test
	public void givenPassword_whenLowercaseWithDigit_andSizeGreaterThen12_thenDisplayOneErrorMessage() {

		final String testValue = "abcdfh23456789";
		validatorModel.setPassword(testValue);
		Set<ConstraintViolation<Validator>> constraintViolations = localValidatorFactory.validate(validatorModel);
		Assertions.assertEquals(true, constraintViolations.size() == 1);
	}

	@Test
	public void givenPassword_whenLowercaseWithDigit_andSameSequenceOfLetter_thenDisplayOneErrorMessage() {

		final String testValue = "andaa12";
		validatorModel.setPassword(testValue);
		Set<ConstraintViolation<Validator>> constraintViolations = localValidatorFactory.validate(validatorModel);
		Assertions.assertEquals(true, constraintViolations.size() == 1);
	}
	
	@Test
	public void givenPassword_whenLowercaseWithDigit_andSameSequenceOfDigit_thenDisplayOneErrorMessage() {

		final String testValue = "anda112";
		validatorModel.setPassword(testValue);
		Set<ConstraintViolation<Validator>> constraintViolations = localValidatorFactory.validate(validatorModel);
		Assertions.assertEquals(true, constraintViolations.size() == 1);
	}

	@Test
	public void givenPassword_whenLowercaseWithDigit_andWithUpperCase_andSizeSmallerThen5_thenDisplayThreeErrorMessage() {

		final String testValue = "Abb";
		validatorModel.setPassword(testValue);
		Set<ConstraintViolation<Validator>> constraintViolations = localValidatorFactory.validate(validatorModel);
		Assertions.assertEquals(true, constraintViolations.size() == 3);
	}
	
	@Test
	public void givenPassword_whenLowercaseWithDigit_andSizeBetween5And12_thenDisplayNoErrorMessage() {

		final String testValue = "abc1234";
		validatorModel.setPassword(testValue);
		Set<ConstraintViolation<Validator>> constraintViolations = localValidatorFactory.validate(validatorModel);
		Assertions.assertEquals(true, constraintViolations.size() == 0);
	}
	
	

}
