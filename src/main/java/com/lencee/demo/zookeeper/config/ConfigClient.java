/**
 * 包名：com.lencee.demo.zookeeper.config
 * 文件名：ConfigClient.java
 * 版本信息：
 * 日期：2015年1月23日-下午2:15:49
 * 
 */
package com.lencee.demo.zookeeper.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * <p>配置应用端<p>
 * <p>读取分布式配置</p>
 * @version 2015年1月23日 下午2:15:49
 * 
 */
public class ConfigClient implements Watcher{
	
	// Zookeeper集群服务地址与端口
	private static String zkUrl = "192.168.0.101:11001";

	// 配置结点根路径
	private final static String ROOT = "/myConf";
	// 结点鉴权方式
	private final static String AUTH_TYPE = "digest";
	// 结点鉴权密码
	private final static String AUTH_PWD = "password";

	private ZooKeeper zk = null;
	
	private String url;
	private String username;
	private String pwd;
	
	public ConfigClient() {
		try {
			ZooKeeper zk = new ZooKeeper(zkUrl, 3000,this);
			while (zk.getState() != ZooKeeper.States.CONNECTED) {
				Thread.sleep(3000);
				System.out.println();
			}
			
			//给这个链接添加认证信息
			zk.addAuthInfo(AUTH_TYPE, AUTH_PWD.getBytes());
			this.zk = zk;
			//读取服务端的配置
			reflushValue();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * 
	 * reflushValue：<p>将配置服务设置的配置更新到对象中</p> 
	 * @since  1.0.0
	 */
	public void reflushValue() {
		try {
			this.url = new String(this.zk.getData(ROOT + "/mysql/octopus/url", true, null));
			this.username = new String(this.zk.getData(ROOT + "/mysql/octopus/username", true, null));
			this.pwd = new String(this.zk.getData(ROOT + "/mysql/octopus/pwd", true, null));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public void printValues(){
		System.out.println("----------------当前配置---------------");
		System.out.println("mysql.url:"+this.url);
		System.out.println("mysql.username:"+this.username);
		System.out.println("mysql.pwd:"+this.pwd);
	}
	
	@Override
	public void process(WatchedEvent event) {
		EventType eventType = event.getType();
		if(Watcher.Event.EventType.None==eventType){
			System.out.println("事件：连接服务成功");
		}else if(Watcher.Event.EventType.NodeCreated==eventType){
			System.out.println("事件：节点创建成功");
		}else if(Watcher.Event.EventType.NodeChildrenChanged==eventType){
			System.out.println("事件：子节点更新成功");
			reflushValue();
			printValues();
		}else if(Watcher.Event.EventType.NodeDataChanged==eventType){
			System.out.println("事件：节点更新成功");
			reflushValue();
			printValues();
		}else if(Watcher.Event.EventType.NodeDeleted==eventType){
			System.out.println("事件：节点删除成功");
		}
		
	}

	/**
	 * url
	 *
	 * @return  the url
	 * @since   1.0.0
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * username
	 *
	 * @return  the username
	 * @since   1.0.0
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * pwd
	 *
	 * @return  the pwd
	 * @since   1.0.0
	 */
	public String getPwd() {
		return pwd;
	}

	public static void main(String[] args) throws Exception {
		ConfigClient cc = new ConfigClient();
		System.out.println("客户端开始运行"+cc);
		while(true){
			Thread.sleep(3000);
		}
	}
}
