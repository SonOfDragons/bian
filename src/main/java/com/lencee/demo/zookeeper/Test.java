/**
 * 包名：com.lencee.demo.zookeeper
 * 文件名：Test.java
 * 版本信息：
 * 日期：2015年1月21日-上午9:57:05
 * 
 */
package com.lencee.demo.zookeeper;

import java.util.Arrays;


/**
 * 
 * <p>TODO:类名称<p>
 * <p>TODO:描述本类实现的功能作用，若为接口应该声明调用地址</p>
 * @version 2015年1月21日 上午9:57:05
 * 
 */
public class Test {

	/**
	 * main：<p>描述方法的实现目的、调用约束等；若为接口列举调用示例（调用地址、参数传递格式、调用方法）</p>
	 * @param args 
	 * @since  1.0.0
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "020103-4-0201";
		test.split("0201",2);
		System.out.println(Arrays.toString(test.split("-")));
		System.out.println(Arrays.toString(test.split("-",1)));
		System.out.println(Arrays.toString(test.split("-",2)));
	}

}
