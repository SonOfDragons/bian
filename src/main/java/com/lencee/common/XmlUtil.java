package com.lencee.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XmlUtil {

	/**
	 * XML字符串转JAVA对象 JAVA根对象在类名上必须使用注解@XmlRootElement
	 * 
	 * @param xmlStr
	 *            xml字符串
	 * @param clazz
	 *            JAVA对象的类
	 * @return
	 */
	public static <T> T xmlstrToBean(String xmlStr, Class<?> clazz) {
		T xmlObject = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			Object unmarshalObject = unmarshaller.unmarshal(new StringReader(
					xmlStr));
			xmlObject = (T) unmarshalObject;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlObject;
	}

	/**
	 * JAVA对象转xml字符串 JAVA根对象在类名上必须使用注解@XmlRootElement
	 * 
	 * @param xmlObjce
	 * @param clazz
	 * @return
	 */
	public static <T> String beanToXmlstr(T xmlObjce, Class<?> clazz) {
		String xmlStr = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			
			StringWriter sw = new StringWriter();
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(xmlObjce, sw);
			xmlStr = sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlStr;
	}

	/**
	 * JAVA对象转xml字符串 JAVA根对象在类名上必须使用注解@XmlRootElement
	 * @param xmlObjce
	 * @param clazz
	 * @param encode 声明XML的 encoding="encode"
	 * @return
	 */
	public static <T> String beanToXmlstr(T xmlObjce, Class<?> clazz,String encode) {
		String xmlStr = "";
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			
			StringWriter sw = new StringWriter();
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encode);
			marshaller.marshal(xmlObjce, sw);
			xmlStr = sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlStr;
	}
	
	/**
	 * 获取XML中的指定结点数据
	 * @param xmlStr
	 * @param nodeName
	 * @return
	 */
	public static String getSingleNodeValue(String xmlStr, String nodeName) {
		String ret = "";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(xmlStr.getBytes()));
			NodeList list = doc.getElementsByTagName(nodeName);
			if(list!=null&&list.getLength()>0){
			Node node = list.item(0);
			ret = node.getTextContent();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
