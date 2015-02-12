/**
 * 包名：com.lencee.demo.beanvalidate
 * 文件名：StatusValidator.java
 * 版本信息：
 * 日期：2014年12月25日-上午11:00:44
 * 
 */
package com.lencee.demo.beanvalidate;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<Status, String> {
	private final String[] ALL_STATUS = { "created", "paid", "shipped", "closed" };

	public void initialize(Status status) {
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (Arrays.asList(ALL_STATUS).contains(value))
			return true;
		return false;
	}
}