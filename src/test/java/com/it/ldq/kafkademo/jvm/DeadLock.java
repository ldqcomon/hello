package com.it.ldq.kafkademo.jvm;

import com.it.ldq.kafkademo.entity.Company;

/**
 * @Auther: ldq
 * @Date: 2020/3/5
 * @Description:
 * @Version: 1.0
 */
public class DeadLock {

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1+s2;
        System.out.println(s3==s4);


        Company c1 = new Company();
        Company c2 = new Company();
        //线程1 线程2 相互等待对方释放锁  这样就造成了死锁
        new Thread(()->{
            synchronized (c1) {
                System.out.println("线程1锁住了对象1.......");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (c2){
                    System.out.println("线程1锁住了对象2.......");
                }
            }
        },"线程1").start();

        new Thread(()->{
            synchronized (c2) {
                System.out.println("线程2锁住了对象2.......");
                synchronized (c1){
                    System.out.println("线程2锁住了对象1.......");
                }
            }
        },"线程2").start();
    }
}
