/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SampleBack
 * Author:   coderlong
 * Date:     2018/10/28 21:03
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
public class SampleBack  implements  CallBack{
    @Override
    public void call() {
        System.out.println("the task is starting");
        // 模拟一段耗费时间的任务
        for (int i = 0; i < 1000; i++) {

        }
    }
}