/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Curator_Create_Node
 * Author:   coderlong
 * Date:     2018/11/9 20:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/9
 * @since 1.0.0
 */
public class Curator_Create_Node {
    public static void main(String[] args) throws Exception {
        String path = "/zk-book/c1";
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        client.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        Thread.sleep(15000);
        System.out.println("已成功创建节点");
    }
}