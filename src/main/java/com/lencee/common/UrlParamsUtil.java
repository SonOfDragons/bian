package com.lencee.common;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * URL参数帮助类
 * @author		jacksyen
 * @created	2014年8月11日-下午1:41:13
 */
public class UrlParamsUtil {
	
	/**
	 * URL连接字符串字符
	 */
	public static final String URL_PARAMS_SPLIT = "&";
	
	/**
	 * URL连接字符串等号
	 */
	public static final String URL_PARAMS_EQUAL = "=";
	
	/**
	 * 判断是否属于规定数据类型
	 * Byte、Short、Integer、Long、Float、Double、Character、Boolean、String、BigDecimal
	 * @param 	type	对象Class
	 * @return	boolean
	 * @since	2014年8月18日-上午9:38:27
	 * @author	jacksyen
	 */
	private static boolean isBasicType(Class<?> type){
		Class<?>[] basicType = {Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, 
				Character.class, Boolean.class, String.class, BigDecimal.class};
		for(Class<?> cls : basicType){
			if(type.isAssignableFrom(cls)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取请求参数字符串
	 * 用&字符连接，类似：mm=111&ss=321312
	 * @param 	obj				对象
	 * @param 	supportExtends	是否支持继承类
	 * @return	String
	 * @since	2014年8月11日-下午2:31:13
	 * @author	jacksyen
	 */
	public static String getParams(Object obj, boolean supportExtends){
		StringBuffer sb = new StringBuffer();
		try{
			Class<?> c = obj.getClass();
			int i = 0;
			while(true){
				Field[] fields = c.getDeclaredFields(); 
				for(Field field : fields){
					field.setAccessible(true);
					// 如果不等于基本数据类型
					if(!isBasicType(field.getType())){
						continue;
					}
					if(i!=0){
						sb.append(URL_PARAMS_SPLIT);
					}
					Object value = field.get(obj) == null ? "": field.get(obj);
					sb.append(field.getName()).append(URL_PARAMS_EQUAL).append(value);
					i++;
				}
				c = c.getSuperclass();
				if(c.getDeclaredFields().length == 0){
					break;
				}
				if(!supportExtends){
					break;
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 获取请求参数字符串
	 * 不使用继承类
	 * 用&字符连接，类似：mm=111&ss=321312
	 * @param 	obj		对象
	 * @return	String
	 * @since	2014年8月11日-下午2:31:13
	 * @author	jacksyen
	 */
	public static String getParams(Object obj){
		return getParams(obj, false);
	}
	
	/**
	 * 获取请求参数字符串
	 * 用&字符连接，类似：mm=111&ss=321312
	 * @param 	obj			对象
	 * @param	exception	例外字段
	 * @return	String
	 * @since	2014年8月11日-下午2:31:13
	 * @author	jacksyen
	 */
	public static String getParams(Object obj, String...exception){
		StringBuffer sb = new StringBuffer();
		try{
			Field[] fields = obj.getClass().getDeclaredFields(); 
			int i = 0;
			for(Field field : fields){
				field.setAccessible(true);
				if(isException(field.getName(), exception)){
					continue;
				}
				// 如果不等于基本数据类型
				if(!isBasicType(field.getType())){
					continue;
				}
				if(i!=0){
					sb.append(URL_PARAMS_SPLIT);
				}
				Object value = field.get(obj) == null ? "": field.get(obj);
				sb.append(field.getName()).append(URL_PARAMS_EQUAL).append(value);
				i++;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 获取请求参数字符串
	 * @param 	params	Map集合
	 * @return	String
	 * @since	2014年8月11日-下午2:52:10
	 * @author	jacksyen
	 */
	public static String getParams(Map<String, String> params){
		StringBuffer sb = new StringBuffer();
		try{
			List<String> keys = new ArrayList<String>(params.keySet());
			int i = 0;
			for(String key : keys){
				if(i!=0){
					sb.append(URL_PARAMS_SPLIT);
				}
				sb.append(key).append(URL_PARAMS_EQUAL).append(params.get(key));
				i++;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 解析URL参数成Map集合
	 * @param 	params	URL字符串参数
	 * @return	Map<String, String>
	 * @since	2014年8月11日-下午2:51:43
	 * @author	jacksyen
	 */
	public static Map<String, String> parseUrlParams(String params){
		Map<String, String> map = new HashMap<String, String>();
		try{
			int len = params.length();
			StringBuilder temp = new StringBuilder();
			String curChar;
			String key = null;
			boolean isKey = true;
		
			for (int i = 0; i < len; i++) {// 遍历整个带解析的字符串
				curChar = String.valueOf(params.charAt(i));// 取当前字符
				if (curChar.equals(URL_PARAMS_SPLIT)) {// 如果读取到&分割符
					putKeyValueToMap(temp, isKey, key, map, "UTF-8");
					temp.setLength(0);
					isKey = true;
				} else {
					if (isKey) {// 如果当前生成的是key
						if (curChar.equals(URL_PARAMS_EQUAL)) {// 如果读取到=分隔符
							key = temp.toString();
							temp.setLength(0);
							isKey = false;
						} else {
							temp.append(curChar);
						}
					} else {// 如果当前生成的是value
						temp.append(curChar);
					}
				}
			}
			putKeyValueToMap(temp, isKey, key, map, "UTF-8");
		} catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 插入键和值到新的map中
	 * @param 	temp	需要插入值字符串
	 * @param 	isKey	是否为为键
	 * @param 	key		键
	 * @param 	map		新的map
	 * @param	charset	字符编码
	 * @throws UnsupportedEncodingException
	 */
	private static void putKeyValueToMap(StringBuilder temp, boolean isKey,
			String key, Map<String, String> map, String charset) throws UnsupportedEncodingException {
		if (isKey) {
			key = temp.toString();
			if (key.length() == 0) {
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, "");
		} else {
			if (key.length() == 0) {
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, URLDecoder.decode(temp.toString(), charset));
		}
	}
	
	/**
	 * 是否在例外字段集合内
	 * @param 	fieldName	字段名称
	 * @param 	exception	例外字段集合
	 * @return	boolean
	 * @since	2014年8月11日-下午4:36:17
	 * @author	jacksyen
	 */
	private static boolean isException(String fieldName, String...exception){
		for(String exec : exception){
			if(StringUtils.equals(fieldName, exec)){
				return true;
			}
		}
		return false;
	}
}
