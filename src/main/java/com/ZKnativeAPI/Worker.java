/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Worker
 * Author:   coderlong
 * Date:     2018/11/8 13:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ZKnativeAPI;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 〈注册从节点〉<br>
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/8
 * @since 1.0.0
 */
public class Worker implements Watcher {
    private static final Logger LOG = LoggerFactory.getLogger(Worker.class);
    ZooKeeper zk;
    String hostport;
    String status;
    String serverID = Integer.toHexString(new Random().nextInt());

    public Worker(String hostport) {
        this.hostport = hostport;
    }
    void startZK() throws IOException {
        zk = new ZooKeeper(hostport, 15000, this);
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        LOG.info(watchedEvent.toString() + ", " + hostport);
    }
    void register() {
        zk.create("/workers/worker-" + serverID,
                "Idle".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                createWorkerCallBack, null);
    }
    AsyncCallback.StringCallback createWorkerCallBack = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    register(); // 连接丢失，重试
                    break;
                case OK:
                    LOG.info("Registered  successfully: " + serverID);
                    break;
                    // 节点已存在
                case NODEEXISTS:
                    LOG.warn("Already registered:" + serverID);
                    break;
                default:
                    LOG.error("出错:" + KeeperException.create(KeeperException.Code.get(rc)));

            }
        }
    };

    // 加锁的方法
    synchronized private void updateStatus(String status) {
        if (status == this.status) {
            zk.setData("/workers/" + serverID, status.getBytes(), -1, statusUpdateCallBack, status);

        }
    }
    public void setStatus(String status) {
        this.status = status;
        updateStatus(status);
    }

    AsyncCallback.StatCallback statusUpdateCallBack = new AsyncCallback.StatCallback() {
        @Override
        public void processResult(int rc, String path, Object ctx, Stat stat) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:

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
        Worker worker = new Worker("127.0.0.1:2181");
        worker.startZK();

        worker.register();

        TimeUnit.SECONDS.sleep(600); // 休眠三十秒
    }
}