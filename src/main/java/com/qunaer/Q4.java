/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Q4
 * Author:   coderlong
 * Date:     2018/11/3 21:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.qunaer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 〈豆瓣图书小爬虫， 只有一万URL〉<br>
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/3
 * @since 1.0.0
 */
public class Q4 {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\86298\\Desktop\\QFC后端开发&测试开发工程师自学计划\\attachments\\Question 4\\test.txt"));
            String bookPath = "C:\\Users\\86298\\Desktop\\QFC后端开发&测试开发工程师自学计划\\attachments\\Question 4\\book.txt";
            File file = new File(bookPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(bookPath));
            String str;
            int count = 0;
            while ((str = reader.readLine()) != null) {
                count++;
                String bookNum = str;
                String bookInfo = getBookInfo(bookNum);
                writer.write(bookInfo + "\n");
                writer.newLine();
                writer.flush();
                System.out.println(bookInfo + "\n");
                Thread.sleep(1000);
                if (count > 200) {
                    break;
                }
            }


        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }


    }


    /**
     *  根据图书编号，发起HTTP请求获取图书信息
     *  @param bookNum  图书编号
     */
    public static String getBookInfo(String bookNum) {
        String res = "";
        try {
            String baseUri = "https://api.douban.com/v2/book/";
            URL url = new URL(baseUri + bookNum);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"utf-8");
            BufferedReader br = new BufferedReader(isr);
            String str;
            String jsonStr = "";
            while ((str = br.readLine()) != null) {
                jsonStr += str;
            }
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = (JsonObject) parser.parse(jsonStr);
            res = "{title:" +jsonObject.get("title") + ", author:" + jsonObject.get("author") + ", summary:" + jsonObject.get("summary") + ", publisher:" + jsonObject.get("publisher") + "}" ;
            res =  res.replaceAll("\\n      ", "暂无该信息");
//            System.out.println(jsonObject.get("title"));
//            System.out.println(jsonObject.get("author"));
//            System.out.println(jsonObject.get("summary"));
//            System.out.println(jsonObject.get("publisher"));
//      System.out.println(jsonObject.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return res;

    }
}