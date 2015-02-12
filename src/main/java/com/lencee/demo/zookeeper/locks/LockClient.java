/**
 * 包名：com.lencee.demo.zookeeper.locks
 * 文件名：LockClient.java
 * 版本信息：
 * 日期：2015年1月23日-下午4:49:48
 * 
 */
package com.lencee.demo.zookeeper.locks;

import java.util.Collections;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 
 * <p>TODO:类名称<p>
 * <p>TODO:描述本类实现的功能作用，若为接口应该声明调用地址</p>
 * @version 2015年1月23日 下午4:49:48
 * 
 */
public class LockClient {

	// Zookeeper集群服务地址与端口
	private static String zkUrl = "192.168.0.101:11001";

	// 配置结点根路径
	private final static String ROOT_LOCK = "/lock";
	private final static String WAIT_LOCK = "/lockwait";
	
	private final static String SELF_PATH = "/client";
	private final static String SELF_DATA = "/client";
	
	private ZooKeeper zk = null;
	private boolean iswait = true;
	//锁路径
	private String lockPath;
	//等待路径
	private String selfWaitPath;
	//监听前置锁路径
	private String waitPath;
	
	public LockClient(){
		try {
			ZooKeeper zk = new ZooKeeper(zkUrl,3000,new Watcher(){
				@Override
				public void process(WatchedEvent event) {
					try {
						if(event.getType()==EventType.NodeDeleted){
							System.out.println(event.getPath()+":"+waitPath);
							getLock();
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}});
			
			while (zk.getState() != ZooKeeper.States.CONNECTED) {
				//System.out.println("connecting:"+zk.getState());
				Thread.sleep(3000);
			}
			
			this.zk = zk;
			
			//创建根结点
			String rootValue = "分布式锁";
			if(zk.exists(ROOT_LOCK, true)==null){
				zk.create(ROOT_LOCK, rootValue.getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			}
			if(zk.exists(WAIT_LOCK, true)==null){
				zk.create(WAIT_LOCK, rootValue.getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			}
			
			//在锁结点上增加子结点
			this.lockPath = addNode(ROOT_LOCK+SELF_PATH,SELF_DATA.getBytes(),CreateMode.EPHEMERAL_SEQUENTIAL);
			
			//在等待结点上增加子结点
			this.selfWaitPath = WAIT_LOCK+this.lockPath.substring(ROOT_LOCK.length());
			addNode(this.selfWaitPath,SELF_DATA.getBytes(),CreateMode.EPHEMERAL);
			
			
			System.out.println("lockpath:"+this.lockPath);
			System.out.println("selfWaitPath:"+this.selfWaitPath);
			
			System.out.println("waitPath:"+this.waitPath);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void getLock() throws Exception {

		//检查本线程是否取到锁
		List<String> list = zk.getChildren(ROOT_LOCK, false);
		Collections.sort(list);
		for(String child:list){
			System.out.println(child);
		}
		
		String lookfor = this.lockPath.substring(ROOT_LOCK.length()+1);
		System.out.println(lookfor);
		
		int index = list.indexOf(lookfor);
		if(index==-1){
			System.out.println("NND,别坑我");
		}else if(index==0){
			//获取到锁
			System.out.println("do something...");
			//删除锁队列
			//zk.delete(this.lockPath, -1);
			
			//删除等待队列
			//zk.delete(this.selfWaitPath, -1);
			
			this.iswait = false;
		}else{
			//未取到锁，侦听前一个节点
			String waitLockPath = list.get(index-1);
			this.waitPath = WAIT_LOCK+"/"+waitLockPath;
			zk.getData(this.waitPath, true, new Stat());
			System.out.println("没取到锁，侦听"+this.waitPath);
		}
	}
	public String addNode(String path,byte[] data,CreateMode createMode) throws Exception{
		String nodePath = null;
		if(!path.startsWith("/")){
			throw new Exception("传入的路径没有以'/'开始");
		}
		if(this.zk.exists(path, true)==null){
			//结点不存在
			nodePath = this.zk.create(path, data, Ids.OPEN_ACL_UNSAFE, createMode);
		}
		return nodePath;
	}
	
	
	/**
	 * iswait
	 *
	 * @return  the iswait
	 * @since   1.0.0
	 */
	public boolean isIswait() {
		return iswait;
	}

	/**
	 * @param iswait the iswait to set
	 */
	public void setIswait(boolean iswait) {
		this.iswait = iswait;
	}

	public static void main(String[] args) throws Exception {
		LockClient lc = new LockClient();
		System.out.println("初始化结束。。。。。");
		Thread.sleep(20*1000);
		lc.getLock();
		while(lc.isIswait());
	}

}
