
/**
 * 包名：com.lencee.common.poi.constraints
 * 文件名：ExcelFormat.java
 * 版本信息：
 * 日期：2014年7月18日-下午1:30:09
 * 
 */
 
package com.lencee.common.poi.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


 /**
 * 数值
 * 类名称：ExcelFormat
 * 类描述：
 * 创建人：liugang
 * 修改时间：2014年7月18日 下午1:30:09
 * 修改备注：
 * @version 1.0.0
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTypeNumber {
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
	 * 是否使用千分位分隔符
	 * 默认为false，不使用
	 * 
	 * @return 
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean thousandSeparator() default false;
}
