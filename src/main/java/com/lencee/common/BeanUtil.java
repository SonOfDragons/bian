package com.lencee.common;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 
 * 
 * <p>对象拷贝工具类<p>
 * @version 2014年12月8日 下午3:11:52
 *
 */
public class BeanUtil {

	/**
	 * 拷贝对象属性到目标对象
	 * <p>
	 * <b>示例</b>
	 * BeanSource bs = new BeanSource();
	 * bs.set(...);
	 * ...
	 * BeanTarget bt = BeanUtil.copyProperties(bs,BeanTarget.class);
	 * 
	 * </p>
	 * 
	 * @param sourceObj 源对象
	 * @param targetClazz 目标类
	 * @return 目标对象
	 */
	public static <T> T copyProperties(Object sourceObj, Class<?> targetClazz) {
		T targetObj = null;
		try {
			targetObj = (T) targetClazz.newInstance();
			BeanUtils.copyProperties(targetObj, sourceObj);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getMessage());
		} catch (InstantiationException e) {
			throw new RuntimeException(e.getMessage());
		}
		return targetObj;
	}

	/**
	 * 拷贝源列表对象到目标列表
	 * 
	 * @param sourceList 源列表
	 * @param targetClazz 目标列表元类型
	 * @return 目标列表
	 */
	public static <T> List<T> copyBeans(List<?> sourceList, Class<?> targetClazz)
			throws InstantiationException, IllegalAccessException {

		if (sourceList == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}
		List<T> targetList = new ArrayList<T>();
		for (Object sourceObj : sourceList) {
			T targetObj = copyProperties(sourceObj, targetClazz);
			targetList.add(targetObj);
		}
		return targetList;
	}
	
	/**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */ 
	public static Map<String, Object> convertBean(Object bean) throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		Class<? extends Object> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}

}
