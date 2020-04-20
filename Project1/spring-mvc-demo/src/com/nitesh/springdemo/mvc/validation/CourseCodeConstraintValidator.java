package com.nitesh.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String>{
	private String courseprefix;
	
	@Override
	public void initialize(CourseCode theCourseCode) {
		courseprefix= theCourseCode.value();
	}
	
	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
		if(theCode == null) return false;
		boolean result= theCode.startsWith(courseprefix);
		return result;
	}

}
