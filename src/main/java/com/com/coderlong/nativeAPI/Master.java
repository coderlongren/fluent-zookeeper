/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Master
 * Author:   coderlong
 * Date:     2018/11/1 9:29
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.com.coderlong.nativeAPI;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import sun.nio.cs.ext.ISCII91;

import java.security.acl.Acl;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 〈同步的Master节点〉<br>
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/1
 * @since 1.0.0
 */
public class Master implements Watcher{
    ZooKeeper zk;
    String hostPort;
    String serverId = Long.toString(new Random().nextLong()); // 唯一标志节点的随机值
    boolean isLeader = false; // 自己是不是主节点? 默认不是主节点
    Master(String hostPort) {
        this.hostPort = hostPort;
    }
    void startZK() throws Exception{
        zk = new ZooKeeper(hostPort, 5000, this);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }
    void stopZK () throws Exception{
        zk.close();
    }
    // 如果存在一个主节点，return true
    boolean checkMaster() {
        while (true) {
            try {
                Stat stat = new Stat();
                byte[] data = zk.getData("/master", false, stat);
                isLeader = new String(data).equals(serverId);
            } catch (KeeperException.NoNodeException e) {
                // 没有master节点 return fasle
                return false;
            } catch (KeeperException.ConnectionLossException e) {

            } catch (KeeperException e) { // 这两个异常还是必须要捕获的啊？

            } catch (InterruptedException e) {

            }

        }
    }
    void runForMaster() {
        while (true) {
            try {
                List<ACL> acl = new ArrayList<>();
                zk.create("/master", serverId.getBytes(),acl, CreateMode.EPHEMERAL);

                isLeader = true;
                break;
            } catch (KeeperException e) { // 这两个异常还是必须要捕获的啊？

            } catch (InterruptedException e) {

            }
            // 如果存在主节点，就退出
            if (checkMaster()) {
                break;
            }
        }

    }
    public static void main(String[] args) throws Exception{
        Master master = new Master("127.0.0.1:2181");
        master.startZK();
        Thread.sleep(30000);
        master.runForMaster();
        if (master.isLeader) {
            System.out.println("自己成为了主节点");
            Thread.sleep(40000);

        } else {
            System.out.println("其他人成为了主节点");
        }

        master.stopZK();

    }
}