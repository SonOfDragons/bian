package com.lencee.demo.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.lencee.common.BeanUtil;


public class BeanUtilTest {

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {

		BeanA ba = new BeanA();
		ba.setName("南風不競");
		ba.setAge(111);
		ba.setOld(12);
		ba.setShowtime("20141011");
		
		List<BeanC> list = new ArrayList<BeanC>();
		BeanC bc1 = new BeanC();
		bc1.setTestx("馳");
		list.add(bc1);
		
		BeanC bc2 = new BeanC();
		bc2.setTestx("來");
		list.add(bc2);
		
		BeanC bc3 = new BeanC();
		bc3.setTestx("北"); 
		list.add(bc3);
		
		BeanC bc4 = new BeanC();
		bc4.setTestx("馬");
		list.add(bc4);
		
		
		ba.setList(list);
		
		System.out.println("ba:"+ba.toString());
		
		
		BeanB bb = BeanUtil.copyProperties(ba, BeanB.class);
		System.out.println("bb:"+bb.toString());

	}

}
