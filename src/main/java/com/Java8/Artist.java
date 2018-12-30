/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Artist
 * Author:   coderlong
 * Date:     2018/11/2 14:50
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.Java8;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 〈创作音乐的个人或团体〉<br>
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/2
 * @since 1.0.0
 */
public class Artist {
    public String getName() {
        return name;
    }

    public List<String> getMembers() {
        return members;
    }

    public String getOrigin() {
        return origin;
    }

    public String name;
    public List<String> members;
    public String origin;

    public Artist(String name, List<String> members, String origin) {
        this.name = name;
        this.members = members;
        this.origin = origin;
    }
}