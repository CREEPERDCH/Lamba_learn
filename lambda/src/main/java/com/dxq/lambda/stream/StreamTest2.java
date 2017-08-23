package com.dxq.lambda.stream;
/*
 * Created by CREEPER_D on 2017/8/21.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest2 {

    private boolean compare(int x) {
        System.out.println("执行比较<><><><><><>");
        return x > 5;
    }

    @Test
    public void test() {
        Integer[] ints = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        List<Integer> ret = new ArrayList<>();
        //外部循环:循环的代码和业务处理的代码混淆;
        for (Integer integer : ints) {
            if (integer > 5) ret.add(integer);
            System.out.println(ret);
        }

        //使用stream:内部循环,代码里边不需要写循环的操作
        /*
         * 1.stream是不会存储数据的
         * 2.stream是不会修改源数据的
         * 3.stream是单向,不可重复使用的
         * 4.stream的部分操作是延迟的:
         *      1.调用了一个方法,马上执行,我们叫做迫切执行方法;如果调用了一个方法,并不会立即执行,叫做延迟执行方法
         *      2.只要stream方法返回的是stream,那么这些方法就是延迟执行的方法
         *      3.延迟执行的方法一定要等到一个迫切执行方法执行的时候,才会执行(在Stream里边,返回的不是一个Stream的基本都是迫切执行方法)
         */
        /*Stream.of(ints)
                .filter(x -> x > 5)
                .collect(Collectors.toList())
                .forEach(System.out::println);*/
        Stream<Integer> stream = Stream.of(ints).filter(this::compare);
        System.out.println("--------------------------------------");
        stream.collect(Collectors.toList()).forEach(System.out::println);
    }
}
