/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestDataStream
 * Author:   coderlong
 * Date:     2018/11/3 10:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.Java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/3
 * @since 1.0.0
 */
public class TestDataStream {

    public static void main(String[] args) {
        List<Artist> list = new ArrayList<>();
        Artist a1 = new Artist("a", null, "");
        Artist a2 = new Artist("b", null, "");
        list.add(a1);
        list.add(a2);
        List<String> res = list.stream().map(artist -> artist.getName()).collect(Collectors.toList());
        for (String name : res) {
            System.out.println(name);
        }

    }


}