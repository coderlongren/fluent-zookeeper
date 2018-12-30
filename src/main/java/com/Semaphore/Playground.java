/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Playground
 * Author:   coderlong
 * Date:     2018/11/7 16:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.Semaphore;

import java.util.concurrent.Semaphore;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/7
 * @since 1.0.0
 */
public class Playground {
    // 跑道类
    static class Track {
        private int num;

        public Track(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Track{" +
                    "num=" + num +
                    '}';
        }
    }
    // 五个跑道
    private Track[] tracks = {
            new Track(1), new Track(2)};
    private volatile boolean[] used = new boolean[2];

    private Semaphore semaphore = new Semaphore(2, true);

    /**
     *  获取一个跑道
     */
    public Track getTrack() throws InterruptedException {
        semaphore.acquire(1);
        return getNextAvailableTrack();
    }

    /***
     * 释放一个跑道
     */
    public void releaseTrack(Track track) {
        if (makeAsUsed(track)) {
            semaphore.release(1);
        }
    }

    /***
     * 遍历，找到一个没人用的跑道
     */

    private Track getNextAvailableTrack() {
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                used[i] = true;
                return tracks[i];
            }
        }
        return null;
    }

    private boolean makeAsUsed(Track track) {
        for (int i = 0; i < used.length; i++) {
            if (tracks[i] == track) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }


}