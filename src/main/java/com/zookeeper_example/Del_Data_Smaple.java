/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Del_Data_Smaple
 * Author:   coderlong
 * Date:     2018/10/31 9:51
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
 * @create 2018/10/31
 * @since 1.0.0
 */
public class Del_Data_Smaple {

    public static void main(String[] args) {
        try {
            String  path = "/zk-book";
            // 创建 会话客户端
            ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000);
            zkClient.createPersistent(path, "");
            zkClient.createPersistent(path + "/book1", "");
            zkClient.deleteRecursive(path); // 循环删除吗
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("创建了循环节点");
        }
    }

}