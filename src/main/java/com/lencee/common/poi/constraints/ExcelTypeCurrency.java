
/**
 * 包名：com.lencee.common.poi.constraints
 * 文件名：ExcelTypeMoney.java
 * 版本信息：
 * 日期：2014年7月18日-下午4:28:39
 * 
 */
 
package com.lencee.common.poi.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


 /**
 * 货币
 * 类名称：ExcelTypeMoney
 * 类描述：
 * 创建人：liugang
 * 修改时间：2014年7月18日 下午4:28:39
 * 修改备注：
 * @version 1.0.0
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTypeCurrency  {
	/**
	 * 数值格式的小数位数
	 * 默认值为2
	 * @return 
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public int decimalDigits() default 2;
	
	/**
	 * 货币符号
	 * 默认为"￥"
	 * @return 
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public String currencySymbol() default "￥";
	
}
