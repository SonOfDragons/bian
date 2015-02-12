package com.lencee.demo.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class XmlList {
	private String te;
	private List<Item> list ;
	public String getTe() {
		return te;
	}
	@XmlAttribute
	public void setTe(String te) {
		this.te = te;
	}
	public List<Item> getList() {
		return list;
	}
	@XmlElement
	public void setList(List<Item> list) {
		this.list = list;
	}
	
	
}
