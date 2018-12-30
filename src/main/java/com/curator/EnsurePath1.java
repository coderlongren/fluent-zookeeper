/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: EnsurePath
 * Author:   coderlong
 * Date:     2018/11/14 21:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/14
 * @since 1.0.0
 */
public class EnsurePath1 {
    static String path = "/zk-book/c1";
    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").sessionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
    public static void main(String[] args) throws Exception {
        client.start();
        client.usingNamespace("zk-book");
        EnsurePath ensurePath = new EnsurePath(path);
        ensurePath.ensure(client.getZookeeperClient());
        ensurePath.ensure(client.getZookeeperClient());
        EnsurePath ensurePath1 = client.newNamespaceAwareEnsurePath("/c1");
        ensurePath1.ensure(client.getZookeeperClient());
        EnsurePath ensurePath2 = new EnsurePath(path + "/c2");
        ensurePath2.ensure(client.getZookeeperClient());
        Thread.sleep(60000);
    }
}