/**
 * 包名：com.lencee.demo.zookeeper.config
 * 文件名：ConfigManager.java
 * 版本信息：
 * 日期：2015年1月23日-下午1:28:55
 * 
 */
package com.lencee.demo.zookeeper.config;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * <p>配置管理类<p>
 * <p>维护分布式配置</p>
 * @version 2015年1月23日 下午1:28:55
 * 
 */
public class ConfigManager {
	
	private static ConfigManager configManager = null;
	
	//Zookeeper集群服务地址与端口
	private static String zkUrl = "192.168.0.101:11001";

	//配置结点根路径
	private final static String ROOT = "/myConf";
	//结点鉴权方式
	private final static String AUTH_TYPE = "digest";
	//结点鉴权密码
	private final static String AUTH_PWD = "password";
	
	private ZooKeeper zk = null;
	
	private ConfigManager(){}
	
	public synchronized static ConfigManager getInstance() throws Exception{
		if (configManager == null) {
			configManager = new ConfigManager();
			ZooKeeper zk = new ZooKeeper(zkUrl, 3000, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					System.out.println("事件类型：" + event.getType());
				}
			});
			while (zk.getState() != ZooKeeper.States.CONNECTED) {
				Thread.sleep(3000);
			}
			
			//给这个链接添加认证信息
			zk.addAuthInfo(AUTH_TYPE, AUTH_PWD.getBytes());
			
			configManager.setZk(zk);
			
			String rootValue = "测试环境配置";
			if(zk.exists(ROOT, true)==null){
				//结点不存在
				zk.create(ROOT , rootValue.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
			}
		}
		return configManager;
	}
	
	public void addNode(String path,byte[] data,CreateMode createMode) throws Exception{
		if(!path.startsWith("/")){
			throw new Exception("传入的路径没有以'/'开始");
		}
		if(this.zk.exists(path, true)==null){
			//结点不存在
			this.zk.create(path, data, Ids.CREATOR_ALL_ACL, createMode);
		}
	}

	public void setDate(String path,byte[] data) throws Exception{
		if(this.zk.exists(path, true)==null){
			addNode(path, data, CreateMode.PERSISTENT);
		}else{
			zk.setData(path, data, -1);
		}
		
	}
	/**
	 * zk
	 *
	 * @return  the zk
	 * @since   1.0.0
	 */
	public ZooKeeper getZk() {
		return zk;
	}

	/**
	 * @param zk the zk to set
	 */
	public void setZk(ZooKeeper zk) {
		this.zk = zk;
	}
	
	public static void main(String[] args) throws Exception {
		ConfigManager cfm = ConfigManager.getInstance();
		
		//添加数据库配置节点
		String path = ROOT+"/mysql";
		String value = "测试环境Mysql配置";
		cfm.setDate(path, value.getBytes());
		
		//添加项目配置节点
		String octopusPath = ROOT+"/mysql/octopus";
		String octopusValue = "资源系统";
		cfm.setDate(octopusPath, octopusValue.getBytes());
		
		//添加连接URL的配置
		String urlPath = ROOT+"/mysql/octopus/url";
		String urlValue = "jdbc:mysql://test.xxx.com:3306/octopus?useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
		cfm.setDate(urlPath, urlValue.getBytes());
		//添加连接数据库的用户名配置
		String userPath = ROOT+"/mysql/octopus/username";
		String userValue = "octopus";
		cfm.setDate(userPath, userValue.getBytes());
		//添加连接数据库的密码配置
		String pwdPath = ROOT+"/mysql/octopus/pwd";
		String pwdValue = "octopus111";
		cfm.setDate(pwdPath, pwdValue.getBytes());
	}
}
