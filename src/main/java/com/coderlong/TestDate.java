/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestDate
 * Author:   coderlong
 * Date:     2018/10/31 10:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.coderlong;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/10/31
 * @since 1.0.0
 */
public class TestDate {
    public static void main(String[] args) {
        try {
            Thread.sleep(5 * 1000);

        } catch (Exception e) {
            System.out.println(e.getStackTrace());

        } finally {
            System.out.println("这个代码需要运行五秒钟吗");
        }
    }
}