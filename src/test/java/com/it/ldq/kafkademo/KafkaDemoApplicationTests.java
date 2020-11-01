package com.it.ldq.kafkademo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class KafkaDemoApplicationTests {

    public int textLoads() {
        try {
            int i;
            int a = 10/0;
           return i = 1+2;
        } catch (Exception e) {
            e.printStackTrace();
            return 666;
        }
       // return 100;
    }

    public static void main(String[] args) {
        int i = new KafkaDemoApplicationTests().textLoads();
        System.out.println(i);
    }

}
