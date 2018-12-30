/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: PreconditoonTest
 * Author:   coderlong
 * Date:     2018/11/6 13:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.guava;

import com.google.common.base.Preconditions;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/6
 * @since 1.0.0
 */

public class PreconditoonTest {
    public static void main(String[] args) {
        try {
            sqrt(-5.0);
            sum(null, 4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static double sqrt(double input) throws IllegalArgumentException{
        Preconditions.checkArgument(input > 0.0,
                 "Illegal Argument possed: Negative value %s", input
        );
        return Math.sqrt(input);
    }

    public static int sum(Integer a, Integer b) {
        Preconditions.checkNotNull(a, "Illegal Arguments %d", a);
        Preconditions.checkNotNull(b, "Illegal Arguments %d", b);
        return a + b;
    }
}