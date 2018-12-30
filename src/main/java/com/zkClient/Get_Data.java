/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Get_Data
 * Author:   coderlong
 * Date:     2018/11/9 15:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zkClient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.data.Stat;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/9
 * @since 1.0.0
 */
public class Get_Data {
    public static void main(String[] args) throws Exception{
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 5000);
        String path = "/zl-book";
        zkClient.createEphemeral(path, "123");
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(String path, Object data) throws Exception {
                System.out.println("znode: " + path + "data change" + data.toString());
            }

            @Override
            public void handleDataDeleted(String path) throws Exception {
                System.out.println("znode:" + path + "was deleted");
            }
        });
        Stat stat = new Stat();
        System.out.println(zkClient.readData(path).toString());
        zkClient.writeData(path, "456");
        Thread.sleep(1000);
        zkClient.delete(path);
        Thread.sleep(Integer.MAX_VALUE);

    }
}