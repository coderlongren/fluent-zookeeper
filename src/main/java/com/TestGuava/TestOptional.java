/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestOptional
 * Author:   coderlong
 * Date:     2018/11/1 16:08
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.TestGuava;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author coderlong
 * @create 2018/11/1
 * @since 1.0.0
 */
public class TestOptional {
    public static void main(String[] args) {

        Optional<Integer> a = Optional.of(null);
        Optional<Integer> b = Optional.of(new Integer(10));
        System.out.println(sum(a, b));

        // 先决条件
//        Preconditions condition =
    }
    public static Integer sum(Optional<Integer> a , Optional<Integer> b) {
        System.out.println(a.isPresent());
        System.out.println(b.isPresent());

        return a.get() + b.get();
    }
}