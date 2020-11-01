package com.it.ldq.kafkademo.jvm;

import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: ldq
 * @Date: 2020/3/5
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class TestStackOverflow {
    public static int couunt;

    public static void main(String[] args) {
        try {
            m1();
            System.out.println(couunt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void m1() {
        couunt++;
       // m1();
    }
}
