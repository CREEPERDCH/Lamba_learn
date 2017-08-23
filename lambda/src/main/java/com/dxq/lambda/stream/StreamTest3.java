package com.dxq.lambda.stream;
/*
 * Created by CREEPER_D on 2017/8/23.
 */

import com.dxq.lambda.User;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest3 {

    /**
     * 把数组变成Stream:Arrays.stream()
     */
    @Test
    public void testArrayStream1() {
        //通过Array.Stream(ints)得到一个IntStream,IntStream是一种特殊的Stream
        int[] ints = new int[]{1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(ints);
        //针对对象的数组:Arrays.stream->Stream<T>
        User[] us = new User[]{new User("Hah", 1), new User("Heh", 2)};
        Stream<User> userStream = Arrays.stream(us);
    }

    /**
     * 调用Stream.of()来完成
     */
    @Test
    public void testArrayStream2() {
        int[] ints = new int[]{1, 2, 3, 4, 5};
        //注意不能直接把简单类型的数组作为Stream.of的参数,返回的是Stream<int[]>
        Stream<int[]> intsStream = Stream.of(ints);
        //得到一个Stream<Integer>,这个Stream里的操作会涉及到autobox unbox
        //一定要注意Stream<Integer>和IntStream是不一样的对象
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
    }

    /**
     * 集合类型 -> Stream
     */
    @Test
    public void testColStream() {
        //对于集合,直接调用对应的stream()方法
        List<String> strs = Arrays.asList("a", "b", "c");
        Stream<String> stringStream = strs.stream();

        //得到并行执行的Stream
        Stream<String> parallelStringStream = strs.parallelStream();
    }

    @Test
    public void testEmptyStream() {
        //创建一个空的Stream
        Stream<Object> empty = Stream.empty();
    }

    /**
     * 如果可以在遍历Stream元素的时候,才去生成要处理的下一个元素,就有可能创建一个无限大的Stream
     * 延迟
     * 可以创建大量的数据
     */
    @Test
    public void testUnlimitStream1() {
//        Stream.generate(() -> "Hah")
//                .forEach(System.out::println);
        //limit 截取limit个数据
//        Stream.generate(() -> "haha")
//                .limit(50)
//                .forEach(System.out::println);
//        Stream.generate(() -> new User("haha", 2))
//                .limit(100)
//                .forEach(System.out::println);
        Stream.generate(Math::random)
                .limit(20000)
                .forEach(System.out::println);
    }

    /**
     * 产生规律的数字
     * 使用Stream.iterate来产生均匀的数据
     */
    @Test
    public void testUnlimitStream2() {
//        Stream.iterate(0, UnaryOperator.identity())
//                .limit(100000)
//                .forEach(System.out::println);
        Stream.iterate(0, x -> x + 1)
                .limit(100000)
                .forEach(System.out::println);
    }
}
