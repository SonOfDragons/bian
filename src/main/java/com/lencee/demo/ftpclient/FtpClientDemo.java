
/**
 * 包名：com.lencee.demo.ftpclient
 * 文件名：FtpClientDemo.java
 * 版本信息：
 * 日期：2014年9月10日-下午5:27:53
 * 
 */
 
package com.lencee.demo.ftpclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.net.ftp.FTPClient;

import com.lencee.common.FtpClientUtil;


 /**
 * 
 * 类名称：FtpClientDemo
 * 类描述：
 * 创建人：liugang
 * 修改时间：2014年9月10日 下午5:27:53
 * 修改备注：
 * @version 1.0.0
 * 
 */

public class FtpClientDemo {

	/**
	 * main(这里用一句话描述这个方法的作用)
	 * @param args 
	 * @exception 
	 * @since  1.0.0
	 */

	public static void main(String[] args) throws SocketException, IOException {
		// TODO Auto-generated method stub
		String host= "192.168.0.100";
		int port = 21;
		String username = "dev_ftp";
		String password = "LKjyyopZ8e5";
		InputStream is = new FileInputStream("D:/身份证号.txt");
		String remotePath = "/ftp/house/";
		String remotFile = "身份证号.txt";
		FTPClient ftpClient = FtpClientUtil.getClient(host, port, username, password);
		FtpClientUtil.upload(ftpClient, remotePath, remotFile, is);
		
	}

}
