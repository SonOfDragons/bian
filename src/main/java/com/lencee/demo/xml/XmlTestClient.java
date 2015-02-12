package com.lencee.demo.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.lencee.common.XmlUtil;

public class XmlTestClient {

	public static void main(String[] args) {
//		String xmlStr = "<XmlObject><name>佛剑分说</name><list><item>佛碟</item><item>天佛尊werwrwer</item><item>斩身sfsdfdsfsdfsfsffffffffw</item></list><test>1231312131232111111111213213123123123</test></XmlObject>";
//		long t1  =  System.currentTimeMillis();
//		System.out.println(XmlUtil.getSingleNodeValue(xmlStr, "test"));
//		long t2  =  System.currentTimeMillis();
//		System.out.println(xmlStr.substring(xmlStr.indexOf("<test>")+6, xmlStr.indexOf("</test>")));
//		long t3 = System.currentTimeMillis();
//		
//		System.out.println(t2-t1);
//		System.out.println(t3-t2);
		try {
//			JAXBContext jaxbContext = JAXBContext.newInstance(XmlObject.class);
//			Unmarshaller unmarshaller =jaxbContext.createUnmarshaller();
//			XmlObject xmlObject = (XmlObject) unmarshaller.unmarshal(new StringReader(xmlStr));
//			XmlObject xmlObject = XmlUtil.xmlstrToBean(xmlStr,XmlObject.class);
//			System.out.println(xmlObject.getName());
//			System.out.println(xmlObject.getTest());
//			System.out.println(Arrays.toString(xmlObject.getList().get(0).getItem()));
			
			
			XmlObject xobj = new XmlObject();
			xobj.setTest(1);
			xobj.setName("sdfs低功耗dfs");
//			Item item1 = new Item();
//			item1.setItem("1");
//			list.add(item1);
			
//			Item item2 = new Item();
//			item2.setItem("2");
//			list.add(item2);
			
			
			System.out.println(XmlUtil.beanToXmlstr(xobj,XmlObject.class,"GBK"));
//			
//			StringWriter sw = new StringWriter();
//			Marshaller marshaller = jaxbContext.createMarshaller();
//			marshaller.marshal(xobj, sw);
//			System.out.println(sw.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
