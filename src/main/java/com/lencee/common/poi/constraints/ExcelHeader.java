
/**
 * 包名：com.lencee.common.poi.constraints
 * 文件名：ExcelDateMapper.java
 * 版本信息：
 * 日期：2014年7月18日-上午10:56:56
 * 
 */
 
package com.lencee.common.poi.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


 /**
 * 
 * 类名称：ExcelDateMapper
 * 类描述：
 * 创建人：liugang
 * 修改时间：2014年7月18日 上午10:56:56
 * 修改备注：
 * @version 1.0.0
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelHeader {

	/**
	 * 列的表头标题
	 * 
	 * @return 
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public String title();
	/**
	 * 所在的列号
	 * 
	 * @return 
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public int  column();
}
