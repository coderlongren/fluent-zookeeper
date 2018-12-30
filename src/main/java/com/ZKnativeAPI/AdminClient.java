/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: AdminClient
 * Author:   coderlong
 * Date:     2018/11/8 14:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ZKnativeAPI;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.sql.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/8
 * @since 1.0.0
 */
public class AdminClient implements Watcher {
    ZooKeeper zk;
    String hostport;

    public AdminClient(String hostport) {
        this.hostport = hostport;
    }
    void  startZK() throws Exception{
        zk = new ZooKeeper(hostport, 15000, this);
    }
    void listState() throws KeeperException, InterruptedException {
        try {
            Stat stat = new Stat();
            byte[] masterData = zk.getData("/master", false, stat);
            Date startDate = new Date(stat.getCtime());
            System.out.println("Master" + new String(masterData) + " since " + startDate);

        } catch (KeeperException.NoNodeException e) {
            System.out.println("No master znode");
        }
        System.out.println("Workers:");
        for (String w : zk.getChildren("/workers", false)) {
            byte[] data = zk.getData("/workers/" + w, false, null);
            String state = new String(data);
            System.out.println("\n" + w + ": " + state);
        }

        System.out.println("Tasks:");
        for (String t: zk.getChildren("/assign", false)) {
            System.out.println("\n" + t);
        }

    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws Exception{
        AdminClient adminClient = new AdminClient("127.0.0.1:2181");
        adminClient.startZK();
        adminClient.listState();
    }
}