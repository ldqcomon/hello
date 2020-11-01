package com.it.ldq.kafkademo.juc;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Auther: ldq
 * @Date: 2020/3/10
 * @Description:
 * @Version: 1.0
 */
public class AtomicIntegerFieldUpdateTest {
    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdateTest> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdateTest.class,"count");
    @Getter
    public volatile int count = 100;
        public static void main(String[] args) {
        AtomicIntegerFieldUpdateTest updateTest = new AtomicIntegerFieldUpdateTest();
        if (updater.compareAndSet(updateTest,100,200)) {
            System.out.println("count1:"+updateTest.getCount());
        }
        if (updater.compareAndSet(updateTest,100,300)) {
            System.out.println("count2:"+updateTest.getCount());
        }
        else{
            System.out.println("count1:"+updateTest.getCount());
        }
    }
}
