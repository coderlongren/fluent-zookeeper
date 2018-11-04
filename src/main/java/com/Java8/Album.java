/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Album
 * Author:   coderlong
 * Date:     2018/11/2 14:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.Java8;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/2
 * @since 1.0.0
 */
public class Album {
    public String name;
    public List<Track> tracks;
    public List<Artist> musicians;

    public Album(String name, List<Track> tracks, List<Artist> musicians) {
        this.name = name;
        this.tracks = tracks;
        this.musicians = musicians;
    }

    public String getName() {
        return name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public List<Artist> getMusicians() {
        return musicians;
    }
}