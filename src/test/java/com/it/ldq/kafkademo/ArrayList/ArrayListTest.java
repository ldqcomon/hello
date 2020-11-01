package com.it.ldq.kafkademo.ArrayList;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Auther: ldq
 * @Date: 2020/3/9
 * @Description:
 * @Version: 1.0
 */
public class ArrayListTest {
    public static void main(String[] args) {
        int[] i = {1,2,3};
       // List<Integer> list = Arrays.stream(i).boxed().collect(Collectors.toList());
        List<Integer> list = new ArrayList<>();
        Map hashMap = new HashMap();
        list.add(1);
        list.add(2);
        list.add(3);
       // ArrayList<Integer> list2 = new ArrayList<>(list);
        //固定的长度的集合  不可以改变size 一旦初始化之后 因为它没有实现add 和remove的方法
       // List<Integer> ints = Arrays.asList(1,2,3);
        //过滤掉值为2的元素
       // List<Integer> collect = list2.stream().filter(integer -> integer != 2).collect(Collectors.toList());

        //还可以用迭代器进行遍历和删除元素
       /* Iterator<Integer> iterator = list2.iterator();
        while(iterator.hasNext()) {
            if (iterator.next()==2) iterator.remove();
        }*/

        //java8 的特性   在java8之前是不允许list集合一边遍历一边删除集合中的元素的!!!!!!!!
        list.removeIf(integer -> integer==1);

        System.out.println(list);
    }
}
