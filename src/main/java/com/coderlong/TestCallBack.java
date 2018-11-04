/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestCallBack
 * Author:   coderlong
 * Date:     2018/10/28 21:05
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
 * @create 2018/10/28
 * @since 1.0.0
 */
public class TestCallBack {

    public void countTime(CallBack task) {
        Long start = System.currentTimeMillis();
        task.call();
        Long end = System.currentTimeMillis();
        System.out.println("the task speet " + (end - start) + " millis");
    }

    public static void main(String[] args) {
        TestCallBack main = new TestCallBack();
        SampleBack task = new SampleBack();
        main.countTime(task);
    }
}