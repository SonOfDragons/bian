package com.lencee.demo.httpclient;

import java.util.Map;

import com.lencee.common.HttpClientUtil;

public class InvokeClient implements Runnable{
	private static int count = 0;
	private String url;
	private String urlrel;
	private Map<String,String> paramMap;
	
	
	
	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getUrlrel() {
		return urlrel;
	}



	public void setUrlrel(String urlrel) {
		this.urlrel = urlrel;
	}



	public Map<String, String> getParamMap() {
		return paramMap;
	}



	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}



	public void run() {
		String str = HttpClientUtil.get(url, paramMap);
//		if("{\"status\":\"SUCCESS\"}".equals(str)){
//			if(count<3){
//				count++;
//			}else{
//				System.out.println("no way");
//			}
//			
//		}
		
		System.out.println(str);
		//HttpClientUtil.get(urlrel, paramMap);
	}
} 