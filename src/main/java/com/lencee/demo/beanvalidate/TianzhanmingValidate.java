/**
 * 包名：com.lencee.demo.beanvalidate
 * 文件名：TianzhanmingValidate.java
 * 版本信息：
 * 日期：2014年12月25日-上午11:18:37
 * 
 */
package com.lencee.demo.beanvalidate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
 * <p>TODO:类名称<p>
 * <p>TODO:描述本类实现的功能作用，若为接口应该声明调用地址</p>
 * @version 2014年12月25日 上午11:18:37
 * 
 */
public class TianzhanmingValidate implements ConstraintValidator<Tianzhanming, String> {

	@Override
	public void initialize(Tianzhanming constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if("tianzhanming".equals(value)) {
			return true;
		}
		return false;
	}
}