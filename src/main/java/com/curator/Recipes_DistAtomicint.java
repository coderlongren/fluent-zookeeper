/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Recipes_DistAtomicint
 * Author:   coderlong
 * Date:     2018/11/14 19:31
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/14
 * @since 1.0.0
 */
public class Recipes_DistAtomicint {
    static String path = "/curator_dis";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();

    public static void main(String[] args) throws Exception {
        client.start();
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client,  path, new RetryNTimes(1000,3));
        AtomicValue<Integer> rc = atomicInteger.add(3);
        System.out.println("res " + rc.succeeded());
    }
}