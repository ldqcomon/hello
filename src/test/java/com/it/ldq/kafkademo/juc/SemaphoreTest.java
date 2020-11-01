package com.it.ldq.kafkademo.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: ldq
 * @Date: 2020/3/10
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class SemaphoreTest {
    //private static int count = 0;
    private static AtomicInteger count = new AtomicInteger(0);
    private static int threadNums = 10;
    private static int clientNums = 2000;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientNums);
        final Semaphore semaphore = new Semaphore(threadNums);
        for (int i = 0; i < clientNums; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        //关闭线程池
        executorService.shutdown();
        log.info("count:{}",count);
    }

    public static void add () {
        count.getAndIncrement();
    }
}
