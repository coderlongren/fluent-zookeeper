/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Client
 * Author:   coderlong
 * Date:     2018/11/8 13:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.ZKnativeAPI;

import org.apache.zookeeper.*;

/**
 * 〈 任务队列化〉<br>
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/8
 * @since 1.0.0
 */
public class Client implements Watcher {
    ZooKeeper zk;
    String hostport;

    public Client(String hostprot) {
        this.hostport = hostprot;
    }
    void startZK() throws Exception{
        zk = new ZooKeeper(hostport, 15000, this);
    }

    String queueCommand(String command) throws Exception {
        String name = "";
        while (true) {
            try {
                name = zk.create("/tasks/task-",command.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
                return name;
            } catch (KeeperException.NodeExistsException e) {
                throw  new Exception(name + "快要被运行了");
            } catch (KeeperException.ConnectionLossException e) {

            }
        }
    }
     @Override
    public void process(WatchedEvent watchedEvent) {
         System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws Exception{
        Client client = new Client("127.0.0.1:2181");

        client.startZK();

        String name = client.queueCommand("My command 1");
        System.out.println("Created" + name);
    }
}
