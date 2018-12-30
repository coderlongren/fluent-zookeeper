/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Get_Children
 * Author:   coderlong
 * Date:     2018/11/9 15:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.zkClient;

import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/9
 * @since 1.0.0
 */
public class Get_Children {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1:2181", 15000);
        zkClient.deleteRecursive("/zk-book");
        List<String> children = zkClient.getChildren("/");
        System.out.println(children.toString());
    }
}