package com.lencee.demo.httpclient;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import com.lencee.common.HttpClientUtil;

public class TestClient {

	public static void ssl(){
	}
	public static void main(String[] args) throws InterruptedException {
//		String url = "http://192.168.3.214:8083/payment/order/add";
//		String request = "{'businessType':'UNIONPAY_LENCEE','reconciliationDateStr':'2014-09-02','afilePath':'/ftp/manual/UNIONPAY_LENCEE/UNIONPAY/2014-09/2014-09-02.xls','bfilePath':'/ftp/manual/UNIONPAY_LENCEE/LENCEE/2014-09/2014-09-02.xls'}";
//		Map<String,String> paramMap = new HashMap<String,String>();
//		paramMap.put("paymentUnit", "");
//		paramMap.put("paymentType", "");
//		paramMap.put("paymentNum", "1");
//		long s = System.currentTimeMillis();
//		String ret = HttpClientUtil.post(url, paramMap);
//		System.out.println(System.currentTimeMillis()-s); 
//		System.out.println(ret);
		
//		String url = "http://127.0.0.1:8085/reconciliation/findAccountCheckAccountDetailResult";
//		Map<String,String> paramMap = new HashMap<String,String>();
//		paramMap.put("reconciliationDateStr", "yyyy-MM-dd");
//		//EASYLIFE_LENCEE,FINANCE_PAYMENT
//		paramMap.put("businessType", "EASYLIFE_LENCEE");
//		paramMap.put("start", "1");
//		paramMap.put("count", "1");
//		paramMap.put("id", "12");
//		String ret = HttpClientUtil.get(url, paramMap);
//		System.out.println(ret);
//		String url = "http://192.168.3.174:8086/businessLock/get";
//		String urlrel = "http://192.168.3.174:8086/businessLock/release";
//		String url = "http://192.168.3.175:8083/memberRoulette/getWinner";
//		Map<String,String> paramMap = new HashMap<String,String>();
//		//paramMap.put("request", "{\"bussinessNumber\":\"sfwesawre\",\"serialNumber\":\"bbfdsfsb\"}");
//		paramMap.put("userId", "140326213242750");
//		for(int i=0;i<1000;i++){
//			InvokeClient ic = new InvokeClient();
//			ic.setUrl(url);
//			ic.setUrlrel(urlrel);
//			ic.setParamMap(paramMap);
//			Thread th = new Thread(ic);
//			th.start();
//		}
//		long start = System.currentTimeMillis();
//		for(int i=0;i<50;i++){
//		//long s = System.currentTimeMillis();
//		Thread.sleep(100);
//		//long e = System.currentTimeMillis();
//		//System.out.println(e-s);
//		}
//		long end = System.currentTimeMillis();
//		long s = System.currentTimeMillis();
//		System.out.println(end-start);
//		long e = System.currentTimeMillis();
//		System.out.println(e-s);
		
		String url= "https://ec.test.bank.ecitic.com/pec/e3rdplaceorder.do";
		Map<String,String> map = new HashMap<String,String>();
		map.put("SIGNREQMSG","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");
		String st = HttpClientUtil.post(url, map);
		System.out.println(st);
	}

}
