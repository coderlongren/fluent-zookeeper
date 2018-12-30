/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestExecutors
 * Author:   coderlong
 * Date:     2018/11/11 22:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.Executor;

import java.util.concurrent.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/11
 * @since 1.0.0
 */
public class TestExecutors {
    static ExecutorService tp;
    static CountDownLatch countDownLatch = new CountDownLatch(1);
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService tp = Executors.newFixedThreadPool(3);
        Callable work1 = () -> {
            System.out.println("开始处理一些问题");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
            System.out.println("任务处理完成");
            return "sdfsdf";
        };

        Callable work2 = Executors.callable(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("结束");
            }
        }, 5);

        FutureTask res = (FutureTask) tp.submit(work2);
        System.out.println(res.get());
        tp.shutdown();

    }


}