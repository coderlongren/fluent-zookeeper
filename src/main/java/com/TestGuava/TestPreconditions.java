/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestPreconditions
 * Author:   coderlong
 * Date:     2018/11/1 16:41
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.TestGuava;

import com.google.common.base.Preconditions;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/1
 * @since 1.0.0
 */
public class TestPreconditions {

    public static void main(String[] args) {
        TestPreconditions testPreconditions = new TestPreconditions();
        try {
            System.out.println(testPreconditions.sprt(-3.0));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }



    public double sprt(double input) throws IllegalArgumentException{
        Preconditions.checkArgument(input > 0.0,
        "Illegal Argument passed Negative value %s %s", input, "hello");
        return Math.sqrt(input);
    }


}