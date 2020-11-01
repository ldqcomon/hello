package com.it.ldq.kafkademo.jvm;

/**
 * @Auther: ldq
 * @Date: 2020/3/5
 * @Description:
 * @Version: 1.0
 */
public class JmapDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("1...................");
        Thread.sleep(30000);
        byte[] bytes = new byte[1024 * 1024 * 10];//10m
        System.out.println("2...................");
        Thread.sleep(30000);
        bytes = null;
        System.gc();
        System.out.println("3...................");
        Thread.sleep(20000);
    }
}
