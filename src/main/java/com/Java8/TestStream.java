/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestStream
 * Author:   coderlong
 * Date:     2018/11/2 14:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.Java8;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/2
 * @since 1.0.0
 */
public class TestStream {
    public static void main(String[] args) {
        List<String> tracks = Stream.of("as", "dsfsd", "dsd").collect(Collectors.toList());
        String maxStr = tracks.stream().max(Comparator.comparing(str -> str.length())).get();
        System.out.println(maxStr);
        BinaryOperator<Integer> addAction = (a, b) -> a + b;
        int sum = Stream.of(1,2,3,4,5).reduce(0, addAction);
        System.out.println(sum);
//        int max = Stream.of(1,2,3,4,5).min(Co
//        System.out.println(max);
        Predicate<Integer> test = x -> x > 2;
        Supplier<Integer> supplier = () -> {
          return 1;
        };
        System.out.println(supplier.get());


        // 使用Reduce实现Count方法
        int cou = Stream.of(1,2,3,4).reduce(0, (a, b) -> a + b);


        // flatMap 的应用
        List<Integer> list = Stream.of(Stream.of(1,2,3).collect(Collectors.toList()), Stream.of(4,5).collect(Collectors.toList())).flatMap(numbers -> numbers.stream()).collect(Collectors.toList());
        System.out.println(list.stream().reduce(0, (a, b) -> a + b));

    }
}