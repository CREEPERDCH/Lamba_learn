package com.dxq.lambda.stream;
/*
 * Created by CREEPER_D on 2017/8/21.
 */

import com.dxq.lambda.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamTest1 {

    private List<User> us = new ArrayList<>();

    @Before
    public void prepared() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            us.add(new User("user" + i, random.nextInt(50) + 50));
        }
    }

    //需求1:列出班上超过85分的学生姓名,并且按照分数的降序输出用户名字
    @Test
    public void test1() {
        //传统写法
        List<String> ret = new ArrayList<>();
        List<User> temp = new ArrayList<>();
        for (User u : us) {
            if (u.getScore() > 85) {
                temp.add(u);
            }
        }
        temp.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        });
        for (User u : temp) {
            ret.add(u.getName());
        }
        System.out.println(ret);

        //使用stream
        /*
          1.得到集合的流对象
          2.使用filter方法完成了过滤
          3.使用sorted方法完成了排序
          4.使用map方法把User的流变成了String的流
          5.使用Collect方法把String的流收集成一个List<String>
         */
        ret = us.stream()
                .filter(u -> u.getScore() > 85)
                .sorted(Comparator.comparing(User::getScore).reversed())//revered反向实现降序的需求
                .map(User::getName)
                .collect(Collectors.toList());
        System.out.println(ret);
    }

    //需求2:统计处平均分数
    @Test
    public void test2() {
        //传统写法
        double totalScore = 0D;
        for (User u : us) {
            totalScore += u.getScore();
        }
        if (us.size() > 0) {
            double avg = totalScore / us.size();
            System.out.println(avg);
        }

        //使用Stream
        /*
         *1.变成一个Int的流
         *2.使用average求这个流的平均值
         */
        us.stream()
                .mapToInt(User::getScore)
                .average()
                .ifPresent(System.out::println);
    }
}
