/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Q3
 * Author:   coderlong
 * Date:     2018/11/3 10:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qunaer;

import java.io.*;
import java.util.*;

/**
 * 〈神雕侠侣小说转换〉<br>
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/3
 * @since 1.0.0
 */

public class Q3 {
    public static void main(String[] args) {

        String path = "C:\\Users\\86298\\Desktop\\QFC后端开发&测试开发工程师自学计划\\attachments\\Question 3\\test1.txt";
        String path1 = "C:\\Users\\86298\\Desktop\\QFC后端开发&测试开发工程师自学计划\\attachments\\Question 3\\test2.txt";

        // 皆是下标计算的
        List<String> charOrder = new ArrayList<>();
        List<String> charOrderDESC = new ArrayList<>();
        List<String> natureOrder = new ArrayList<>();
        String[] indexOrder = new String[6110];

        try {
            File file = new File("charOrder.txt");
            File novel = new File("C:\\Users\\86298\\Desktop\\QFC后端开发&测试开发工程师自学计划\\attachments\\Question 3\\novel.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            if (!novel.exists()) {
                novel.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(path));
            BufferedReader reader2 = new BufferedReader(new FileReader(path1));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            BufferedWriter writeNovel = new BufferedWriter(new FileWriter(novel));
            String str;


            Set<Integer> set = new HashSet<>();

            while ((str = reader.readLine()) != null) {
                String[] arr;
                arr = str.split("\t");
                natureOrder.add(arr[1]);
                charOrder.add(arr[1]);
                indexOrder[Integer.valueOf(arr[0])] = arr[1];
                // 计算是否重复Set
                set.add(Integer.valueOf(arr[0]));
            }

            Collections.sort(charOrder );

            // 字符倒排集合
            for (int i = charOrder.size() - 1; i >= 0; i--) {
                charOrderDESC.add(charOrder.get(i));
            }
            System.out.println(charOrder.size());
            System.out.println(set.size());

            System.out.println("----------------------");

            while ((str = reader2.readLine()) != null) {
                char[] chars = str.toCharArray();
                String line = "";
                String name = "";
                boolean flag = true;
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == '$') {
                        flag = false;
                        continue;
                    }
                    if (chars[i] == ')') {
                        String ctx = sdx(name, natureOrder, indexOrder, charOrder, charOrderDESC);
                        line += ctx;
                        name = "";
                        flag = true;
                        continue;
                    }
                    if (flag) {
                        line += chars[i];
                    } else {
                        name += chars[i];
                    }

                }

                writeNovel.write(line);
                writeNovel.newLine();
                System.out.println();
                writeNovel.flush();
            }

        } catch (IOException e) {

        }


    }
    /**
     *  根据相应的规则获得转换后的小说句子
     *  @param sdx 带匹配字符串
     *  @param  natureOrder
     *  @param  indexOrder
     *  @param  charOrder
     *  @param  charOrderDESC
     */
    public static String sdx(String sdx, List<String> natureOrder, String[] indexOrder, List<String> charOrder, List<String> charOrderDESC) {
        int num = getNum(sdx);
        if (sdx.startsWith("natureOrder")) {
            return natureOrder.get(num);
        }
        else if (sdx.startsWith("indexOrder")) {
            return indexOrder[num];
        }
        else if (sdx.startsWith("charOrderDESC")) {
            return charOrderDESC.get(num);
        }
        else {
            return charOrder.get(num);
        }
    }

    /**
     *  获得每一个带匹配字符串末尾的数字
     *  @param sdx 带匹配字符串
     */
    public static int getNum(String sdx) {
        StringBuilder builder = new StringBuilder();
        for (int i = sdx.length() - 1; i >= 0; i--) {
            char c = sdx.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            } else {
                break;
            }
        }
        builder.reverse();
        return Integer.valueOf(builder.toString());
    }
}