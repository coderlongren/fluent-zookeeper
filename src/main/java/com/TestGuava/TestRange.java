/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestRange
 * Author:   coderlong
 * Date:     2018/11/6 22:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.TestGuava;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;

import java.util.Iterator;
import java.util.function.Function;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/6
 * @since 1.0.0
 */
public class TestRange {
    public static void main(String[] args) {

        Function<Integer, Integer> function1 = a -> a * 2; // 函数式接口
        Function<Integer, Integer> function2 = a -> a * a;

        System.out.println(function1.andThen(function2).apply(3));
        System.out.println(function1.compose(function2).apply(3));
        Range<Integer> ranage = Range.closedOpen(1,9);
        printRage(ranage);
        Range<Integer> range2 = Range.greaterThan(9);
        printRage(range2);

//        Iterator<Integer> integerIterator = range2.
        System.out.println("上界:" + range2.hasLowerBound());
        System.out.println("下界:" + range2.hasUpperBound());

    }
    public static void printRage(Range<Integer> range) {
        System.out.print("[ ");
        for(int grade : ContiguousSet.create(range, DiscreteDomain.integers())) {
            System.out.print(grade +" ");
        }
        System.out.println("]");
    }

}