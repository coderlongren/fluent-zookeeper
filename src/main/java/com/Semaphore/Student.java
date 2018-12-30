/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Student
 * Author:   coderlong
 * Date:     2018/11/7 16:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.Semaphore;

import javafx.concurrent.Worker;

import java.util.concurrent.Semaphore;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/7
 * @since 1.0.0
 */
public class Student {
    public static Semaphore semaphore;
    public static void main(String[] args) {
        semaphore = new Semaphore(2);
        Outer outer = new Outer(semaphore);
        worker worker = new worker(semaphore);
        new Thread(() -> {
            for (int i = 0; i < 4; i++) {
                worker.run();
            }
        }).start();
    }

}
class Outer implements Runnable {
    Semaphore semaphore;

    public Outer(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        semaphore.release(1);
        System.out.println("一个工作和离开了");
    }
}
class worker implements Runnable{

    Semaphore semaphore;

    public worker(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run(){
        try {
            semaphore.acquire(1);
            System.out.println("一个工作者进入了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
