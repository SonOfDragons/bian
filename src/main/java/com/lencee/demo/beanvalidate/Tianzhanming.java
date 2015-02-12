/**
 * 包名：com.lencee.demo.beanvalidate
 * 文件名：Tianzhanming.java
 * 版本信息：
 * 日期：2014年12月25日-上午11:18:04
 * 
 */
package com.lencee.demo.beanvalidate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = TianzhanmingValidate.class)  
@Documented
@Target({ElementType.ANNOTATION_TYPE,ElementType.FIELD})  
@Retention(RetentionPolicy.RUNTIME)  
public @interface Tianzhanming {
	String message() default "adasds";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default{};
}