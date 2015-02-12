/**
 * 包名：com.lencee.demo.beanvalidate
 * 文件名：Price.java
 * 版本信息：
 * 日期：2014年12月25日-上午10:59:20
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Constraint(validatedBy = {StatusValidator.class}) 
@Documented 
@Target( { ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD }) 
@Retention(RetentionPolicy.RUNTIME) 
public @interface Status { 
String message() default "不正确的状态 , 应该是 'created', 'paid', shipped', closed'其中之一"; 
Class<?>[] groups() default {}; 
Class<? extends Payload>[] payload() default {}; 
}