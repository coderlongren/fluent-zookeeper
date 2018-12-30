/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ZK_Barrier
 * Author:   coderlong
 * Date:     2018/11/14 20:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/14
 * @since 1.0.0
 */
public class ZK_Barrier {
    static String path = "/curator_barrier";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
    static DistributedBarrier barrier = new DistributedBarrier(client, path);
    public static void main(String[] args) throws Exception {
        client.start();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "正在准备");
                try {
                    barrier.setBarrier();
                    barrier.waitOnBarrier(); // 等待其他人准备好
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "准备好了");

            }).start();
        }
        Thread.sleep(3000);
        barrier.removeBarrier();
    }
}