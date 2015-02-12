/**
 * 包名：com.lencee.demo.mongodb
 * 文件名：TestMongo.java
 * 版本信息：
 * 日期：2014年12月25日-下午2:55:12
 * 
 */
package com.lencee.demo.mongodb;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * 
 * <p>TODO:类名称<p>
 * <p>TODO:描述本类实现的功能作用，若为接口应该声明调用地址</p>
 * @version 2014年12月25日 下午2:55:12
 * 
 */
public class TestMongo {
	
	public static void main(String[] args) throws UnknownHostException {
/*		List<ServerAddress> addresses = new ArrayList<ServerAddress>();
//		ServerAddress address0 = new ServerAddress("192.168.0.101" , 37000);
        ServerAddress address1 = new ServerAddress("192.168.0.101" , 37001);
//        ServerAddress address2 = new ServerAddress("192.168.0.101" , 37002);
//        ServerAddress address3 = new ServerAddress("192.168.0.101" , 37003);
        //addresses.add(address1);
        addresses.add(address1);
        //addresses.add(address3);

        MongoClient client = new MongoClient(addresses);
        
        List<ServerAddress> addresses1 = client.getAllAddress();
        
        for(ServerAddress sa:addresses1){
        	
        	System.out.println(sa.getPort());
        }
        DBCollection coll  = null;
        for(int i=0;i<3;i++){
        DB db = client.getDB( "test");
        
        coll = db.getCollection( "testdb");
        

         // 插入
        BasicDBObject object = new BasicDBObject();
        object.append( "test"+i, "testval"+i );

        coll.insert(object);
        }
        DBCursor dbCursor = coll.find();

         while (dbCursor.hasNext()) {
              DBObject dbObject = dbCursor.next();
              System. out.println(dbObject.toString());
        }
*/
	}

}
