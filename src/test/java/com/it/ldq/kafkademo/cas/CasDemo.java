package com.it.ldq.kafkademo.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: ldq
 * @Date: 2020/3/7
 * @Description:
 * @Version: 1.0
 */
public class CasDemo {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                integer.getAndIncrement();

            }
        }, "线程1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {

                integer.getAndDecrement();
            }
        }, "线程2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //调用线程的join方法可以让主线程处于等待的状态,一直等到线程1和线程2都运行完毕时,
        // 主线程自己就会调用自身的notifyAll()方法将自己唤醒继续执行下面的代码
        System.out.println(integer);
    }
}
