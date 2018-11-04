/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Main
 * Author:   coderlong
 * Date:     2018/11/2 19:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qunaer;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/2
 * @since 1.0.0
 */
public class Main {
    public static void main(String[] args) {
        String s = "/a/c/dd.jdk";
        String[] arr = s.split("/");
        System.out.println("111");
        for (String item : arr) {
            System.out.println(item + item.length());
        }

        List<Integer> list = new ArrayList<>();
        System.out.println(list.size());
    }
}