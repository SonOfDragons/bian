package com.lencee.demo.xml;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import com.lencee.common.ValidateUtil;


public class Item {
	
	private String[] item;
	
	@Length(max=3,min=1)
	private String test;
	

	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
	}
	
	public static void main(String[] args) {
		Item item = new Item();
		item.setTest("adasdasd");
		
		System.out.println(ValidateUtil.baseCheck(item));
	}
}
