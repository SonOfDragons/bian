
/**
 * 包名：com.lencee.demo.excel
 * 文件名：TableTemple.java
 * 版本信息：
 * 日期：2014年9月1日-下午7:37:20
 * 
 */
 
package com.lencee.demo.excel;

import com.lencee.common.poi.constraints.ExcelHeader;


 /**
 * 
 * 类名称：TableTemple
 * 类描述：
 * 创建人：liugang
 * 修改时间：2014年9月1日 下午7:37:20
 * 修改备注：
 * @version 1.0.0
 * 
 */

public class TableTemple {

	@ExcelHeader(column=0,title="姓名")
	private String name;
	@ExcelHeader(column=1,title="年龄")
	private int age;
	@ExcelHeader(column=2,title="地址")
	private String address;
	/**
	 * name
	 *
	 * @return  the name
	 * @since   1.0.0
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * age
	 *
	 * @return  the age
	 * @since   1.0.0
	 */
	
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * address
	 *
	 * @return  the address
	 * @since   1.0.0
	 */
	
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	
	public void setAddress(String address) {
		this.address = address;
	}
	
}
