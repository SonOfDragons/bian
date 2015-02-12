/**
 * 包名：com.lencee.demo.zookeeper
 * 文件名：ZookeeperClient.java
 * 版本信息：
 * 日期：2015年1月16日-下午3:33:56
 * 
 */
package com.lencee.demo.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

/**
 * 
 * <p>TODO:类名称<p>
 * <p>TODO:描述本类实现的功能作用，若为接口应该声明调用地址</p>
 * @version 2015年1月16日 下午3:33:56
 * 
 */
public class ZookeeperClient implements Watcher {

	public static String url = "192.168.0.101:11001";
	private final static String ROOT = "/myConf";
	
	private final static String URL_NODE = ROOT + "/url";
	private final static String USERNAME_NODE = ROOT + "/username";
	private final static String PWD_NODE = ROOT + "/pwd";
	
	private final static String AUTH_TYPE = "digest";
	private final static String AUTH_PWD = "password";
	
	ZooKeeper zk = null;
			
	private String urlValue;
	private String usernameValue;
	private String pwdValue;
	
	public ZooKeeper getZK() throws Exception{
		zk = new ZooKeeper(url,3000,this);
		while(zk.getState()!=ZooKeeper.States.CONNECTED){
			Thread.sleep(3000);
		}
		zk.addAuthInfo(AUTH_TYPE, AUTH_PWD.getBytes());
		System.out.println("连接zookeeper服务器成功！");
		return zk;
	}
	
	public void initValue(){
		try {
			urlValue = new String(zk.getData(URL_NODE, true, null));
			usernameValue = new String(zk.getData(USERNAME_NODE, true, null));
			pwdValue = new String(zk.getData(PWD_NODE, true, null));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * urlValue
	 *
	 * @return  the urlValue
	 * @since   1.0.0
	 */
	public String getUrlValue() {
		return urlValue;
	}

	/**
	 * @param urlValue the urlValue to set
	 */
	public void setUrlValue(String urlValue) {
		this.urlValue = urlValue;
	}

	/**
	 * usrnameValue
	 *
	 * @return  the usrnameValue
	 * @since   1.0.0
	 */
	public String getUsernameValue() {
		return usernameValue;
	}

	/**
	 * @param usrnameValue the usrnameValue to set
	 */
	public void setUsernameValue(String usernameValue) {
		this.usernameValue = usernameValue;
	}

	/**
	 * pwdValue
	 *
	 * @return  the pwdValue
	 * @since   1.0.0
	 */
	public String getPwdValue() {
		return pwdValue;
	}

	/**
	 * @param pwdValue the pwdValue to set
	 */
	public void setPwdValue(String pwdValue) {
		this.pwdValue = pwdValue;
	}

	public static void main(String[] args) throws Exception {
		ZookeeperClient zc = new ZookeeperClient();
		zc.getZK();
		zc.initValue();
		while(true){
			System.out.println(zc.getUrlValue());
			System.out.println(zc.getUsernameValue());
			System.out.println(zc.getPwdValue());
			System.out.println("-----------------------");
			Thread.sleep(5000);
		}
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
			initValue();
		}else if(Watcher.Event.EventType.NodeDataChanged==eventType){
			System.out.println("事件：节点更新成功");
			initValue();
		}else if(Watcher.Event.EventType.NodeDeleted==eventType){
			System.out.println("事件：节点删除成功");
		}
	}

}
