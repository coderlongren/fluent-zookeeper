/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Get_Data_Smaple
 * Author:   coderlong
 * Date:     2018/10/31 22:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zookeeper_example;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/10/31
 * @since 1.0.0
 */
public class Get_Data_Smaple {
    public static void main(String[] args) {
        String path = "/zk-books";
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000);
        zkClient.createEphemeral(path, "123");
        // zkClient.subscribeChildChanges(path, )

        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                System.out.println("Node" + path + "changed, new Data is " + data.toString());
            }

            @Override
            public void handleDataDeleted(String path) throws Exception {
                System.out.println("Node " + path + "deleted");
            }
        });

        // 第一次读取数据
//        System.out.println(zkClient.readData(path));
        // 第一次设置数据
        zkClient.writeData(path, new Integer(12345));
        // 第一次删除数据
        zkClient.delete(path);


    }
}