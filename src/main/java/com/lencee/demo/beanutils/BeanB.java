package com.lencee.demo.beanutils;

import java.util.List;

public class BeanB {

	private String name;
	private Integer age;
	private int old;
	private String other;
	private BeanC beanc;
	private List<BeanD> list;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public int getOld() {
		return old;
	}
	public void setOld(int old) {
		this.old = old;
	}
	
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public BeanC getBeanc() {
		return beanc;
	}
	public void setBeanc(BeanC beanc) {
		this.beanc = beanc;
	}
	public List<BeanD> getList() {
		return list;
	}
	public void setList(List<BeanD> list) {
		this.list = list;
	}
	public String toString(){
		String test = this.name+";"+this.age+";"+this.old+";"+this.other+";";
		if(this.beanc!=null){
			test += this.beanc.getTestx();
		}
		return test;
	}
}
