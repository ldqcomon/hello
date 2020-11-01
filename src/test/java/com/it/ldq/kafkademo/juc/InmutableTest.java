package com.it.ldq.kafkademo.juc;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: ldq
 * @Date: 2020/3/11
 * @Description:
 * @Version: 1.0
 */
public class InmutableTest {
   /* private static Map map = new HashMap();
    static{
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        //初始化之后便不能再修改了  封装为不可变的map对象
        map = Collections.unmodifiableMap(InmutableTest.map);
    }*/
  // private static final ImmutableMap map =   ImmutableMap.of(1,1,2,2,3,3);
   private static final ImmutableMap<Integer,Integer> map =  ImmutableMap.<Integer,Integer>builder().put(1,1).put(2,2).build();
   private static final ImmutableList list = ImmutableList.of(1,2,3);
   private static final ImmutableSet set = ImmutableSet.copyOf(list);

    public static void main(String[] args) {
        set.add(1);
        //list.add(1);
       //map.put(1,91);
       System.out.println(list);
    }
}
