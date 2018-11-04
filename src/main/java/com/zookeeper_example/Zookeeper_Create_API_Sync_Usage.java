/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Zookeeper_Create_API_Sync_Usage
 * Author:   coderlong
 * Date:     2018/11/1 12:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zookeeper_example;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;


/**
 * 〈同步API的测试代码〉<br>
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/1
 * @since 1.0.0
 */


public class Zookeeper_Create_API_Sync_Usage implements Watcher{
    // CountDownLatch 来保证能够看到后台守护进程发送的watchedEvent时间
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, new Zookeeper_Create_API_Sync_Usage());
        System.out.println(zooKeeper.getState());
        connectedSemaphore.await(); // 只有客户端 接收到了连接完成的通知，main进程才继续进行

        String path1 = zooKeeper.create("/zk-test-empermeral", "test1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("success created znode " + path1);
        String path2 = zooKeeper.create("/zk-test", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        System.out.println("success created znode " + path2);


        System.out.println();
    }

    @Override
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            connectedSemaphore.countDown(); // 计数减一
            System.out.println(event);
        }
    }
}