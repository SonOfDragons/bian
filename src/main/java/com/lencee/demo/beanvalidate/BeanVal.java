package com.lencee.demo.beanvalidate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.lencee.common.ValidateUtil;

public class BeanVal {
	
	@NotBlank(message="坑爷啊")
	private Integer a;
	@NotBlank
	private Long b;
	@NotEmpty
	private Double c;
	@NotBlank
	private Float d;
	private int aa;
	@NotNull
	private long bb;
	@NotNull
	private double cc;
	private float dd;
	
	private char e;
	@Tianzhanming
	private String f;
	private Character ee;
	
	public Integer getA() {
		return a;
	}
	public void setA(Integer a) {
		this.a = a;
	}
	public Long getB() {
		return b;
	}
	public void setB(Long b) {
		this.b = b;
	}
	public Double getC() {
		return c;
	}
	public void setC(Double c) {
		this.c = c;
	}
	public Float getD() {
		return d;
	}
	public void setD(Float d) {
		this.d = d;
	}
	public int getAa() {
		return aa;
	}
	public void setAa(int aa) {
		this.aa = aa;
	}
	public long getBb() {
		return bb;
	}
	public void setBb(long bb) {
		this.bb = bb;
	}
	public double getCc() {
		return cc;
	}
	public void setCc(double cc) {
		this.cc = cc;
	}
	public float getDd() {
		return dd;
	}
	public void setDd(float dd) {
		this.dd = dd;
	}
	public char getE() {
		return e;
	}
	public void setE(char e) {
		this.e = e;
	}
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}
	public Character getEe() {
		return ee;
	}
	public void setEe(Character ee) {
		this.ee = ee;
	}
	
	public static void main(String[] args) {
		BeanVal beanVal = new BeanVal();
		beanVal.setF("created");
		String err = ValidateUtil.baseCheck(beanVal);
		System.out.println(err);
	}

}
