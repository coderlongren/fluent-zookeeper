/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Q1
 * Author:   coderlong
 * Date:     2018/11/2 18:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qunaer;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * 〈Request 统计小程序〉<br>
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/2
 * @since 1.0.0
 */
public class Q1 {
    public static void main(String[] args) {
        int GETcount = 0;
        int POSTcount = 0;
        Map<String, Integer> map = new HashMap<>();
        Map<String, HashSet<String>> fronts = new HashMap<>();

        try {

            File file = new File("C:\\Users\\86298\\Desktop\\QFC后端开发&测试开发工程师自学计划\\attachments\\Question 1\\test.log");
            ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
//            String str;
            for (String str : lines) {
                if (str.startsWith("GET")) {
                    GETcount++;
                } else {
                    POSTcount++;
                }
                String[] arr = str.split(" ");
                if (map.containsKey(arr[1])) {
                    map.put(arr[1], map.get(arr[1]) + 1);
                } else {
                    map.put(arr[1], 1);
                }
                String[] arr2 = arr[1].split("/");


                if (fronts.containsKey(arr2[1])) {
                    fronts.get(arr2[1]).add(arr[1]);
                } else {
                    HashSet<String> temp = new HashSet<>();
                    temp.add(arr[1]);
                    fronts.put(arr2[1], temp);
                }


            }

            // 对Map按照Value进行排序
            List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });

            System.out.println("请求总量" + (GETcount + POSTcount));
            System.out.println("请求最多的十个HTTP接口");
            for (int i = 0; i < 10; i++) {
                System.out.println(list.get(i).getKey() + " : " + list.get(i).getValue());
            }

            System.out.println();
            System.out.println("GET请求 " + GETcount);
            System.out.println("POST请求 " + POSTcount);
            System.out.println("请求分类");

            for(String key : fronts.keySet()) {
                System.out.println("---------------------------------------------------------------");
                System.out.println("请求 /" + key + " 的URI");
                for (String uri : fronts.get(key)) {
                    System.out.println(uri);
                }
                System.out.println("---------------------------------------------------------------");

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }
}