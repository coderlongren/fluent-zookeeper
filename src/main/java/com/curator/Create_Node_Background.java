/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Create_Node_Background
 * Author:   coderlong
 * Date:     2018/11/9 20:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/9
 * @since 1.0.0
 */
public class Create_Node_Background {
    static String path = "/zk-book/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
            .sessionTimeoutMs(5000).retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
    static CountDownLatch countDownLatch = new CountDownLatch(5);
    static ExecutorService tp = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        client.start();
        printInfoCurrThread(Thread.currentThread());
        for (int i = 0; i < 3; i++) {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
                @Override
                public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                    printInfoCurrThread(Thread.currentThread());
                    countDownLatch.countDown(); // 计数器减一
                }
            }, tp).forPath(path, "init".getBytes());
        }
        // 没有交给 Executor线程池运行，实在main线程里面运行的
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                printInfoCurrThread(Thread.currentThread());
                countDownLatch.countDown();
            }
        }).forPath(path);
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                printInfoCurrThread(Thread.currentThread());
                countDownLatch.countDown();
            }
        }).forPath(path, "init".getBytes());

        countDownLatch.await();
        tp.shutdown();

    }
    public static void printInfoCurrThread(Thread curr) {
        System.out.println(curr.getName() + " 正在运行");
    }
}