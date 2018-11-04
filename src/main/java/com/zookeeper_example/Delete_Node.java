/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Delete_Node
 * Author:   coderlong
 * Date:     2018/10/28 21:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zookeeper_example;

import org.I0Itec.zkclient.ZkClient;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/10/28
 * @since 1.0.0
 */
public class Delete_Node {
    public static void main(String[] args) {
        String path = "/workers";
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000);
        String deletePath = "/workers";

        boolean success = zkClient.delete(deletePath);

        if (success) {
            System.out.println("已经成功删除了一个节点");
        }
        else {
            System.out.println("删除节点出现了异常");
        }

        boolean exited = exitNode(zkClient, "/paths");

        if (exited) {
            System.out.println("指定的节点存在");
        }
        else {
            System.out.println("指定的节点不存在");
        }


    }
    public static boolean exitNode(ZkClient clint, String path) {
        return clint.exists(path);
    }
}