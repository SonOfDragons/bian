
/**
 * 包名：com.lencee.common.poi.constraints
 * 文件名：ExcelTypeDateTime.java
 * 版本信息：
 * 日期：2014年7月18日-下午4:36:05
 * 
 */
 
package com.lencee.common.poi.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


 /**
 * 日期
 * 类名称：ExcelTypeDateTime
 * 类描述：
 * 创建人：liugang
 * 修改时间：2014年7月18日 下午4:36:05
 * 修改备注：
 * @version 1.0.0
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelTypeDateTime {

	/**
	 * 源日期时间格式化模式
	 * <li>日期类型(Date/Calendar)：不需要指定</li>
	 * <li>字符串类型(String)：必须指定</li>
	 * @return 
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public String sourcePattern() default "";
	/**
	 * 目标日期时间的格式化模式
	 * <li>日期类型(Date/Calendar)：必须指定</li>
	 * <li>字符串类型(String)：必须指定</li>
	 * @return 
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public String targetPattern();
	
}
