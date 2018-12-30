/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: SemaphoreDemo
 * Author:   coderlong
 * Date:     2018/11/7 20:02
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.Semaphore;

import org.I0Itec.zkclient.ExceptionUtil;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/7
 * @since 1.0.0
 */
public class SemaphoreDemo {
    static class Student implements Runnable{
        private int num;
        private Playground playground;

        public Student(int num, Playground playground) {
            this.num = num;
            this.playground = playground;
        }

        @Override
        public void run() {
            try {
                // 尝试获取跑道
                Playground.Track track = playground.getTrack();
                if (track != null) {
                    // 成功获取到了跑道
                    System.out.println("学生" + num + "在" + track.toString() + "上奔跑");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("学生" + num + "在" + track.toString() + "跑到了终点");
                    playground.releaseTrack(track);
                }

            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        Playground playground = new Playground();
        for (int i = 0; i < 11; i++) {
            new Thread(new Student(i + 1, playground)).start();
        }
    }
}