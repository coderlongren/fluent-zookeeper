/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Auth_Smaple_Get
 * Author:   coderlong
 * Date:     2018/10/31 22:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zookeeper_example;

import org.apache.curator.utils.ZookeeperFactory;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * 〈简单验证 Zookeeper的身份认证〉<br>
 * 〈〉
 *
 * @author coderlong
 * @create 2018/10/31
 * @since 1.0.0
 */
public class Auth_Smaple_Get {
    final static String PATH = "/zk-book-auth_test";
    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper1 = new ZooKeeper("127.0.0.1:2181", 5000, null);

        zooKeeper1.addAuthInfo("digest", "foo:true".getBytes());
        //  create a ephemeeral node
        zooKeeper1.create(PATH, "init".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.EPHEMERAL);
        System.out.println("success create znode" + PATH);

        ZooKeeper zookeeper2 = new ZooKeeper("127.0.0.1:2181", 5000, null);
        zookeeper2.getData(PATH, false, null);

    }

}