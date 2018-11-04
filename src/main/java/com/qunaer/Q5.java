/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Q5
 * Author:   coderlong
 * Date:     2018/11/4 10:36
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
import java.time.temporal.Temporal;

/**
 * 〈模拟Linux命令处理和管道〉<br>
 * 〈限于 cat+ 文件名 输出文件， wc -l 统计行数, group txt 过滤包含txt的行， 以及三种命令通过管道分隔符 | 组合〉
 *
 * @author coderlong
 * @create 2018/11/4
 * @since 1.0.0
 */

public class Q5 {

    public static void main(String[] args) throws Exception{
        Q5 main = new Q5();
//        System.out.println(main.grep("test.txt", "exception", false));
        String command = "cat test.txt | grep exception | grep abcd | wc -l";
        String[] arr = command.split("\\|");
//        for (String item : arr) {
//            System.out.println(main.shell(item, ""));
//        }
        System.out.println(main.shell(command, ""));
    }

    /***
     *
     * @param command shell命令
     * @return 执行Shell命令后的结果
     */

    public String shell(String command, String preRes) throws Exception{
        try {
            command = command.trim();
            String[] arr = command.split("\\|");
            String tempRes = "";
            //  单命令 无管道传递
            if (arr.length == 1) {
                if (arr[0].startsWith("cat")) {
                    String[] argv = arr[0].split(" ");
                    String res = cat(argv[1], false);
                    return res;
                }
                else if (arr[0].startsWith("grep")) {
                    String[] argv = arr[0].split(" ");
                    if (argv.length == 3) {
                        String res = grep(argv[2], argv[1], false);
//                        System.out.println(res);
                        return res;
                    } else if (argv.length == 2) {
                        String res = grep(preRes, argv[1], true);
                        return res;
                    }
                }
                else {
                    String[] argv = arr[0].split(" ");
                    if (argv.length == 3) {
                        int res = wc(argv[2], false);
                        return res + "";
                    } else if (argv.length == 2) {
                        int res = wc(preRes, true);
                        return res + "";
                    }
                }
            }

            // 有管道符 传递文本
            else {
                for (int i = 0; i < arr.length; i++) {
                    tempRes = shell(arr[i], tempRes);
                }
                return tempRes;
            }
            return tempRes;
        } catch (Exception e) {
            throw e;
        }

    }

    /***
     *
     * @param path 不经过管道传递，path 是文件的地址， 经由管道传递，path是传递的文本内容
     * @param flag 命令是否经由管道传递 true or false
     * @return cat命令处理之后得到的文本
     */
    public String cat(String path, boolean flag) throws IOException {
        String res = "";
        if (flag) {
            return path;
        } else {
            File file = new File(path);
            if (!file.exists()) {
                throw new IOException("该文件不存在");
            }
            else {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String str;
                while ((str = reader.readLine()) != null) {
                    res += (str + "\n");
                }
            }
            return res;
        }
    }

    /***
     *
     * @param path 不经过管道传递，path 是文件的地址， 经由管道传递，path是传递的文本内容
     * @param flag  命令是否经由管道传递 true or false
     * @return 统计文件之后得到的 行数
     */
    public int wc(String path, boolean flag) throws Exception{
        int res = 0;
        if (flag) {
            int lines = 0;
            for (char c : path.toCharArray()) {
                if (c == '\n') {
                    lines++;
                }
            }
            return lines;
        } else {
            File file = new File(path);
            if (!file.exists()) {
                throw new IOException("该文件不存在");
            }
            else {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String str;
                while ((str = reader.readLine()) != null) {
                    res++;
                }
            }
            return res;
        }
    }

    /***
     *
     * @param path 不经过管道传递，path 是文件的地址， 经由管道传递，path是传递的文本内容
     * @param flag  命令是否经由管道传递 true or false
     * @param word 需要过滤的文本
     * @return 所有的 包含文本的行
     */
    public String grep(String path, String word, boolean flag) throws IOException{
        String res = "";
        if (flag) {
            String[] arr = path.split("\n");
            for (String item : arr) {
                if (word.length() <= item.length()) {
                    for (int i = 0; i <= item.length() - word.length(); i++) {
                        String temp = item.substring(i, i + word.length());
                        if (temp.equals(word)) {
                            res += (item + "\n");
                        }
                    }
                }
            }
            return res;
        } else {
            File file = new File(path);
            if (!file.exists()) {
                throw new IOException("该文件不存在");
            }
            else {
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String str;
                while ((str = reader.readLine()) != null) {
                    if (word.length() <= str.length()) {
                        for (int i = 0; i <= str.length() - word.length(); i++) {
                            String temp = str.substring(i, i + word.length());
                            if (temp.equals(word)) {
                                res += (str + "\n");
                            }
                        }
                    }
                }
            }
            return res;
        }
    }
}