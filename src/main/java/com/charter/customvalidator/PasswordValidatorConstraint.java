package com.charter.customvalidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@NotBlank(message= "Password Must Not be Empty.")
@Size(min = 5, max = 12, message = "Password must be between 5 and 12 characters long.")
@Pattern(regexp = "^(?=[0-9a-z]{1,}$)(?=[^a-z]*[a-z])(?=[^0-9]*[0-9]).*", message = "Password must contain at least one digit and lower character.")
@Pattern(regexp = "^(?!.*([a-z0-9])\\1{1,}).+$", message = "Password must not contain any sequence of characters immediately followed by the same sequence.")
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface PasswordValidatorConstraint {

	String message() default "Invalid password.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}