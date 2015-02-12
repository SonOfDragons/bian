
/**
 * 包名：com.lencee.common.poi
 * 文件名：ExcelProperty.java
 * 版本信息：
 * 日期：2014年9月1日-下午5:09:00
 * 
 */
 
package com.lencee.common.poi;


 /**
 * 
 * 类名称：ExcelProperty
 * 类描述：
 * 创建人：liugang
 * 修改时间：2014年9月1日 下午5:09:00
 * 修改备注：
 * @version 1.0.0
 * 
 */

public class ExcelProperty {
	/**
	 * 指定工作空间名
	 */
	private String defaultSheetName;
	/**
	 * 当文件存在时，是否需要覆盖
	 */
	private boolean isCreatNew;
	/**
	 * 是否跳过首行
	 */
	private boolean ignorHeader;
	/**
	 * defaultSheetName
	 *
	 * @return  the defaultSheetName
	 * @since   1.0.0
	 */
	
	public String getDefaultSheetName() {
		return defaultSheetName;
	}
	/**
	 * @param defaultSheetName the defaultSheetName to set
	 */
	
	public void setDefaultSheetName(String defaultSheetName) {
		this.defaultSheetName = defaultSheetName;
	}
	/**
	 * isCreatNew
	 *
	 * @return  the isCreatNew
	 * @since   1.0.0
	 */
	
	public boolean isCreatNew() {
		return isCreatNew;
	}
	/**
	 * @param isCreatNew the isCreatNew to set
	 */
	
	public void setCreatNew(boolean isCreatNew) {
		this.isCreatNew = isCreatNew;
	}
	/**
	 * ignorHeader
	 *
	 * @return  the ignorHeader
	 * @since   1.0.0
	 */
	
	public boolean isIgnorHeader() {
		return ignorHeader;
	}
	/**
	 * @param ignorHeader the ignorHeader to set
	 */
	
	public void setIgnorHeader(boolean ignorHeader) {
		this.ignorHeader = ignorHeader;
	}
	

}
