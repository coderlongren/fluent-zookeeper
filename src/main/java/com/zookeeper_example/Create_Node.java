/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Create_Node
 * Author:   coderlong
 * Date:     2018/10/28 11:07
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zookeeper_example;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/10/28
 * @since 1.0.0
 */
public class Create_Node {
    public static void main(String[] args) {

        ZkClient zkClient = new ZkClient("127.0.0.1:2181");
        String path = "/paths";
        zkClient.createPersistent(path, true);
        System.out.println("success create znode");

    }

}