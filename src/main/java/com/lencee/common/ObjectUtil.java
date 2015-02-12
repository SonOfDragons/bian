/**
 * 包名：com.lencee.common
 * 文件名：ObjectUtil.java
 * 版本信息：
 * 日期：2014年12月31日-下午5:19:04
 * 
 */
package com.lencee.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>TODO:类名称<p>
 * <p>TODO:描述本类实现的功能作用，若为接口应该声明调用地址</p>
 * @version 2014年12月31日 下午5:19:04
 * 
 */
public class ObjectUtil {

	//用于全局存储
	public static Map<String, String> GLOBAL_STORE = new HashMap<String, String>();
	
	public static Object defaultIfBlank( Object object, Object defaultObject ){
		if( ObjectUtil.isBlank( object ) )
			return defaultObject;
		return object;
	}
	
	/** 是否null*/
	public static boolean isBlank( Object object ){
		return null == object;
	}
	
	/***
	 * check if originalObjectArray
	 * @return true if have one blank at least.
	 */
	public static boolean isBlank( Object... originalObjectArray ) {

		if ( null == originalObjectArray || 0 == originalObjectArray.length )
			return true;
		for ( int i = 0; i < originalObjectArray.length; i++ ) {
			if ( isBlank( originalObjectArray[i] ) )
				return true;
		}
		return false;
	}
	
}