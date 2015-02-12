/**
 * 包名：com.lencee.demo.zookeeper
 * 文件名：ZookeeperClient.java
 * 版本信息：
 * 日期：2015年1月16日-下午3:33:56
 * 
 */
package com.lencee.demo.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * <p>TODO:类名称<p>
 * <p>TODO:描述本类实现的功能作用，若为接口应该声明调用地址</p>
 * @version 2015年1月16日 下午3:33:56
 * 
 */
public class ZookeeperServer {

	public static String url = "192.168.0.101:11001";
	private final static String ROOT = "/myConf";
	
	private final static String URL_NODE = ROOT + "/url";
	private final static String USERNAME_NODE = ROOT + "/username";
	private final static String PWD_NODE = ROOT + "/pwd";
	
	private final static String AUTH_TYPE = "digest";
	private final static String AUTH_PWD = "password";
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper(url,3000,new Watcher(){

			@Override
			public void process(WatchedEvent event) {
				System.out.println("事件类型："+event.getType());
			}
		});
		
		while(zk.getState() != ZooKeeper.States.CONNECTED){
			Thread.sleep(3000);
		}
		
		zk.addAuthInfo(AUTH_TYPE, AUTH_PWD.getBytes());
		
		String rootValue = "测试环境数据库配置";
		if(zk.exists(ROOT, true)==null){
			zk.create(ROOT, rootValue.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
		}
		
		String urlValue = "jdbc:mysql://192.168.0.101:3306/octopus?useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
		if(zk.exists(URL_NODE, true)==null){
			zk.create(URL_NODE, urlValue.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
		}else{
			zk.setData(URL_NODE, urlValue.getBytes(), -1);
		}
		
		String usernameValue = "octopus123";
		if(zk.exists(USERNAME_NODE, true)==null){
			zk.create(USERNAME_NODE, usernameValue.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
		}else{
			zk.setData(USERNAME_NODE, usernameValue.getBytes(), -1);
		}
		
		String pwdValue = "octopus";
		if(zk.exists(PWD_NODE, true)==null){
			zk.create(PWD_NODE, pwdValue.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
		}else{
			zk.setData(PWD_NODE, pwdValue.getBytes(), -1);
		}
		
		zk.close();
	}

}
