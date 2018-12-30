/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FindJavaJStack
 * Author:   coderlong
 * Date:     2018/11/10 13:01
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
 * @create 2018/11/10
 * @since 1.0.0
 */

public class FindJavaJStack {
    public static void main(String[] args) {
        new Thread(new Worker()).start();
    }
}

class Worker implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("The Thread name is " + Thread.currentThread().getName());
        }
    }
}
