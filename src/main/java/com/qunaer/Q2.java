/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Q2
 * Author:   coderlong
 * Date:     2018/11/2 22:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qunaer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 〈去除注释统计有效代码行，小程序〉<br>
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/2
 * @since 1.0.0
 */
public class Q2 {
    public static void main(String[] args) {
        File file = new File("StringUtils.java");
        String code = "";
        String str;
        try {
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while ((str = reader.readLine()) != null) {

                    str = str.replaceAll(" ", "");
                    //  去除空行 和  单行注释
                    if (str.length() > 0 && !str.startsWith("//")) {
                        code += (str + "\n");
                    }
                }
                int count = 0;
                int j = 0;
                for (char c : code.toCharArray()) {
                    if (c == '\n') {
                        System.out.print("???" + j++ + c);
                    } else {
                        System.out.print(c);
                    }
                }
                for (char c : code.toCharArray()) {
                    if (c == '\n') {
                        count++;
                    }
                }
                System.out.println("count " + count);
                StringBuilder builder = new StringBuilder();
                int flag = 0;

                // 处理 <!--  ? ? ?--> 的多重嵌套注释
                for (int i = 0; i < code.length(); i++) {
                    if(flag == 0) {
                        if (i + 1 < code.length() && code.charAt(i) == '<' && code.charAt(i + 1) == '!') {
                            flag++;
                            i++;
                            continue;
                        }
                    } else {
                        if (i +2 < code.length() && code.charAt(i) == '-' && code.charAt(i + 1) == '-' && code.charAt(i + 2) == '>') {
                            flag--;
                            i++;
                            continue;
                        }
                    }
                    if (flag == 0) {
                        builder.append(code.charAt(i));
                    }
                }
//                System.out.println(code);
                int line = 0;
                for (char c : builder.toString().toCharArray()) {
                    if (c == '\n') {
                        line++;
                    }
                }
                System.out.println("有效代码行为 ：" + line);
            }
            else {
                System.out.println("该文件不存在");
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}