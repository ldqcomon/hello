package com.it.ldq.kafkademo.volatiles;

import java.util.concurrent.CountDownLatch;

/**
 * @Auther: ldq
 * @Date: 2020/3/8
 * @Description:
 * @Version: 1.0
 */
public class VolatileTest {
    public static volatile int race = 0;
    public static void increase() {
        race++;
    }
    private static final int THREADS_COUNT = 20;
    private static CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);
    public static void  main(String[] args) throws InterruptedException {
        for (int i= 0; i < THREADS_COUNT; i++) {
           new Thread(new Runnable(){
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increase();
                    }
                    countDownLatch.countDown();
                    System.out.println(countDownLatch.getCount());
                }
            }).start();
            //threads[i].join(); //等待上一个线程执行完后,再依次执行下个线程的任务
        }
        /*while (Thread.activeCount() > 1) { //这个循环是死循环!!当线程醒来又被暂停无限循环!!
            Thread.yield();//让出cpu的使用权 但是不可以被中断
        }*/
       //Thread.sleep(5000);
       /* for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i].join();
        }*/
        countDownLatch.await();//等待所有的子线程结束
        //执行主方法的程序
        System.out.println(race); //结果多数是小于20000 也有等于20000的时候但是较少出现
    }
}
