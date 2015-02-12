package com.lencee.demo.beanutils;

import java.util.List;

public class BeanA {

	private String name;
	private Integer age;
	private int old;
	private String showtime;
	
	private List<BeanC> list;
	
	private BeanC beanc;
	
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
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	public BeanC getBeanc() {
		return beanc;
	}
	public void setBeanc(BeanC beanc) {
		this.beanc = beanc;
	}
	public List<BeanC> getList() {
		return list;
	}
	public void setList(List<BeanC> list) {
		this.list = list;
	}
	public String toString(){
		String test = this.name+";"+this.age+";"+this.old+";"+this.showtime+";"+this.beanc+";"+this.list;
		return test;
	}

}
