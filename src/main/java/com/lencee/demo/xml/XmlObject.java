package com.lencee.demo.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="aaa")
public class XmlObject {
	
	private String name;
	
	private int test;
	
	private XmlList xmlList;
	
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	@XmlAttribute
	public int getTest() {
		return test;
	}
	
	public void setTest(int test) {
		this.test = test;
	}
	public XmlList getXmlList() {
		return xmlList;
	}
	@XmlElement
	public void setXmlList(XmlList xmlList) {
		this.xmlList = xmlList;
	}
	
	
}
