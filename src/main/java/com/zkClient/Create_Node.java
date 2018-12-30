/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Create_Node
 * Author:   coderlong
 * Date:     2018/11/9 15:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zkClient;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/9
 * @since 1.0.0
 */
public class Create_Node {
    public static void main(String[] args) throws Exception{
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 15000);

        String path = "/zk-book/c1";
        zkClient.createPersistent(path, true);
        System.out.println("success create znode");
        TimeUnit.SECONDS.sleep(30);
    }
}