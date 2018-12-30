/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestServer
 * Author:   coderlong
 * Date:     2018/11/14 21:49
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/14
 * @since 1.0.0
 */
public class TestServer {
    static String path = "/zookeeper";

    public static void main(String[] args) throws Exception {
        TestingServer server = new TestingServer(2181, new File("C:\\zookeeper\\zookeeper-3.4.10\\zookeeper-3.4.10\\bin\\data\\zk-data"));
        TestingServer server2 = new TestingServer(2182, new File("C:\\zookeeper\\zookeeper-3.4.10\\zookeeper-3.4.11\\bin\\data\\zk-data"));
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(server.getConnectString()).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        CuratorFramework client2 = CuratorFrameworkFactory.builder().connectString(server2.getConnectString()).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

        client.start();
        client2.start();
        System.out.println(client.getChildren().forPath(path));
        System.out.println(server.getConnectString());
        System.out.println(server.getPort());
        System.out.println(server.getTempDirectory());
        System.out.println(client.getChildren());

        System.out.println(client2.getChildren().forPath(path));
        System.out.println(server2.getConnectString());
        System.out.println(server2.getPort());
        System.out.println(server2.getTempDirectory());
        System.out.println(client2.getChildren());

        server.close();
        server2.close();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}