
/**
 * 包名：com.lencee.common
 * 文件名：NumericUtil.java
 * 版本信息：
 * 日期：2014年7月25日-下午2:03:06
 * 
 */
 
package com.lencee.common;

import java.math.BigDecimal;


 /**
 * 
 * 类名称：NumericUtil
 * 类描述：
 * 创建人：liugang
 * 修改时间：2014年7月25日 下午2:03:06
 * 修改备注：
 * @version 1.0.5
 * 
 */

public class NumericUtil {
	/**
	 * 比较两个浮点数
	 * 
	 * @param a
	 * @param b
	 * @return 
	 * 					<li>-1 : a<b </li>
	 * 					<li>0 :  a=b</li>
	 * 					<li>1 :  a>b</li>
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public static int compareTo(double a,double b){
		BigDecimal bigForA = new BigDecimal(a);
		BigDecimal bigForB = new BigDecimal(b);
		int ret = bigForA.compareTo(bigForB);
		return ret ;
	}
	/**
	 * 比较两个浮点数字符串
	 * 
	 * @param a
	 * @param b
	 * @return 
	 * 					<li>-1 : a<b </li>
	 * 					<li>0 :  a=b</li>
	 * 					<li>1 :  a>b</li>
	 * 
	 * @exception 
	 * @since  1.0.0
	 */
	public static int compareTo(String a,String b){
		BigDecimal bigForA = new BigDecimal(a);
		BigDecimal bigForB = new BigDecimal(b);
		int ret = bigForA.compareTo(bigForB);
		return ret ;
	}
	
	/**
	 * 判断 value 是否与 preset集合中的某个值相等
	 * 
	 * @param value
	 * @param preset
	 * @return 
	 * @return boolean 等于其中之一则返回 true，都不相等则返回 false
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isInPreset(BigDecimal value,String...preset){
		boolean ret =false;
		for(String pre:preset){
			BigDecimal bigPre = new BigDecimal(pre);
			int t = bigPre.compareTo(value);
			if(t==0){
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	/**
	 * a+b+...
	 * 
	 * @param values 进行累加的动态数组
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String add(String...values){
		BigDecimal bigForRet = new BigDecimal(0);
		for(String value:values){
			BigDecimal bigValue = new BigDecimal(value);
			bigForRet = bigForRet.add(bigValue);
		}
		return bigForRet.toString();
	}
	
	/**
	 * a-b
	 * 
	 * @param a
	 * @param b
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String sub(String a,String b){
		BigDecimal bigForA = new BigDecimal(a);
		BigDecimal bigForB = new BigDecimal(b);
		BigDecimal bigForRet = bigForA.subtract(bigForB);
		return bigForRet.toString();
	}
	
	/**
	 * a*b*...
	 * 
	 * @param values 进行累乘的动态数组
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String multip(String...values){
		BigDecimal bigForRet = new BigDecimal(0);
		for(String value:values){
			BigDecimal bigValue = new BigDecimal(value);
			bigForRet = bigForRet.multiply(bigValue);
		}
		return bigForRet.toString();
	}
	
	/**
	 * a/b
	 * 
	 * @param a
	 * @param b
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String division(String a,String b){
		BigDecimal bigForA = new BigDecimal(a);
		BigDecimal bigForB = new BigDecimal(b);
		BigDecimal bigForRet = bigForA.divide(bigForB);
		return bigForRet.toString();
	}
	
	/**
	 * 将a的小数点左移n位
	 * 
	 * @param a
	 * @param n
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String movePointLeft(String a,int n){
		BigDecimal bigForA = new BigDecimal(a);
		bigForA = bigForA.movePointLeft(n);
		return bigForA.toString();
	}
	
	/**
	 * 将a的小数点右移n位
	 * 
	 * @param a
	 * @param n
	 * @return 
	 * @exception 
	 * @since  1.0.0
	 */
	public static String movePointRight(String a,int n){
		BigDecimal bigForA = new BigDecimal(a);
		bigForA = bigForA.movePointRight(n);
		return bigForA.toString();
	}
	
}
