/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Fate_ID_Genarator
 * Author:   coderlong
 * Date:     2018/11/13 14:37
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.curator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/13
 * @since 1.0.0
 */
public class Fate_ID_Genarator {
    static volatile CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();

                } catch (Exception e) {

                }
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                String orderID = sdf.format(new Date());
                System.out.println("订单号： " + orderID);

            }).start();
        }
        countDownLatch.countDown();

    }
}