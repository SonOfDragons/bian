
/**
 * 包名：com.lencee.demo
 * 文件名：ExcelDemo.java
 * 版本信息：
 * 日期：2014年9月1日-上午11:48:13
 * 
 */
 
package com.lencee.demo.excel;

import java.io.IOException;

import com.lencee.common.poi.ExcelProperty;
import com.lencee.common.poi.ExcelUtil;


 /**
 * 
 * 类名称：ExcelDemo
 * 类描述：
 * 创建人：liugang
 * 修改时间：2014年9月1日 上午11:48:13
 * 修改备注：
 * @version 1.0.0
 * 
 */

public class ExcelDemo {
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, IOException {
		
		TableTemple obj = new TableTemple();
		obj.setName("梵天");
		obj.setAge(6);
		obj.setAddress("云渡山");
		
		String file = "D:/Excel测试/中原第一人.xls";
		
		ExcelProperty ep = new ExcelProperty();
		ep.setDefaultSheetName("小区123");
		ep.setCreatNew(true);
		ep.setIgnorHeader(false);
		ExcelUtil eu = new ExcelUtil(file,ep);
		eu.saveToExcel(null, obj);
	}

}
