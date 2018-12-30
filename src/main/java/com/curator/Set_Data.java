/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Set_Data
 * Author:   coderlong
 * Date:     2018/11/10 11:21
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
import org.apache.zookeeper.data.Stat;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/10
 * @since 1.0.0
 */
public class Set_Data {
    public static void main(String[] args) throws Exception{
        String path = "/zk-book";
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        client.start();
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath(path);
        try {
            int ver = client.setData().withVersion(stat.getVersion()).forPath(path).getVersion();
            System.out.println("新版本是" + ver);
            ver = client.setData().withVersion(stat.getVersion()).forPath(path).getVersion();
            System.out.println(ver);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}