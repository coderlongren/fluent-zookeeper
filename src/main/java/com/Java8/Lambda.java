/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Lambda
 * Author:   coderlong
 * Date:     2018/11/1 21:59
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.Java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 〈一句话功能简述〉
 *  函数式编程的 骚操作 <br>
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/1
 * @since 1.0.0
 */
public class Lambda {

    public static void main(String[] args) {
        String name = "coderlong";
//        name = getName();
        new Thread(() -> System.out.println("hi" + name)).start();
        BinaryOperator<Integer> addAction = (a, b) -> a + b;
        System.out.println(addAction.apply(1,2));
        Predicate<Integer> testNum = x -> x > 5;
        System.out.println(testNum.test(6));

        Predicate<Integer> big5 = x -> x > 2;

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        // 这是Java8的语法
        System.out.println(list.stream().filter(big5).count());

        List<String> collected = Stream.of("a", "bD", "cc").collect(Collectors.toList());
        List<String> upperList = new ArrayList<>();
        upperList = collected.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());

        for (String item : upperList) {
            System.out.println(item);
        }

        List<String> isSafeList = collected.stream().filter(s -> safe(s.charAt(0))).collect(Collectors.toList());

        for (String item : isSafeList) {
            System.out.println(item);
        }

    }
    public static boolean safe(char c) {
        return c == 'c';
    }
    public static String getName() {
        return "shabi";
    }
}
