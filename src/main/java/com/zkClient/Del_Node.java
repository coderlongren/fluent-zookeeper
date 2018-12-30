/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Del_Node
 * Author:   coderlong
 * Date:     2018/11/9 15:13
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zkClient;

import org.I0Itec.zkclient.ZkClient;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/9
 * @since 1.0.0
 */
public class Del_Node {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 15000);

        String path = "/zk-book/c1";
        zkClient.createPersistent(path, true);
        zkClient.deleteRecursive(path);
        System.out.println("success delete the node");

    }
}