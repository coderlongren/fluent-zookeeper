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
package com.ZKnativeAPI;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

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
    // 返回是否存在一个master节点
    boolean checkMaster() {
        while (true) {
            try {
                Stat stat = new Stat();
                byte[] data = zk.getData("/master", false, stat);
                System.out.println(new String(data).toString());
                System.out.println(serverId);
                isLeader = new String(data).equals(serverId);
                return true;
            } catch (KeeperException | InterruptedException e) {
                // 没有master节点 return fasle
                return false;
            }

        }
    }
    // 同步的 runForMaster方法

//    void runForMaster() {
//        while (true) {
//            try {
//                List<ACL> acl = new ArrayList<>();
//                zk.create("/master", serverId.getBytes(),OPEN, CreateMode.EPHEMERAL);
//                //
//                isLeader = true;
//                break;
//            } catch (KeeperException e) {
//
//            } catch (InterruptedException e) {
//
//            }
//            // 如果存在主节点，就退出
//            if (checkMaster()) {
//                break;
//            }
//        }
//
//    }

    // 回调函数
     AsyncCallback.StringCallback masterCreateCallBack = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)) {
                // 连接丢失，但我们还要确定master节点是否被创建了，checkMaster
                case CONNECTIONLOSS:
                    checkMaster();
                    return;
                case OK:
                    isLeader = true;
                    break;
                default:
                    isLeader = false;
            }
            System.out.println(KeeperException.Code.get(rc));
            System.out.println("我" + (isLeader ? "是" : "不是") + "the leader");
        }
    };

// 异步的 runForMaster方法
    void runForMaster() {

        zk.create("/master", serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, masterCreateCallBack, null);


    }
    // 使用异步API来设置元数据
    public void bootstrap() {
        // 没有数据传入这些znode节点，传入空的字节数组
        createParent("/workers", new byte[0]);
        createParent("/assign", new byte[0]);
        createParent("/tasks", new byte[0]);
        createParent("/status", new byte[0]);
    }

    void createParent(String path, byte[] data) {
        zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, createParentCallBack,data);
    }

    AsyncCallback.StringCallback createParentCallBack = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    createParent(path, (byte[]) ctx);
                    break;
                case OK:
                    System.out.println("Parent created");

                    // 节点已存在
                case NODEEXISTS:
                    System.out.println("Parent already registered " + path);

                default:
                    System.out.println("错误" + KeeperException.create(KeeperException.Code.get(rc)));

            }
        }
    };

    public static void main(String[] args) throws Exception{
        Master master = new Master("127.0.0.1:2181");
        master.startZK();
        master.runForMaster();
        Thread.sleep(3000);
        if (master.isLeader) {
            System.out.println("自己成为了主节点");
            Thread.sleep(4000);

        } else {
            System.out.println("其他人成为了主节点");
        }
        // 依次创建 /tasks /assign /worker 目录
//        master.bootstrap();
        Thread.sleep(600000);
        master.stopZK();

    }
}