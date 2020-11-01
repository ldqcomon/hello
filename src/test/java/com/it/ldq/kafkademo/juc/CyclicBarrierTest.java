package com.it.ldq.kafkademo.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Auther: ldq
 * @Date: 2020/3/11
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class CyclicBarrierTest {
   private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    public static void main(String[] args) throws InterruptedException {
        final int clientNum = 100;
        //final int threadNum = 3;
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < clientNum; i++) {
            Thread.sleep(1000);
            final int num = i;
            executorService.execute(()->{
                try {
                    race(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

    public static void race (int i) throws InterruptedException {
        Thread.sleep(1000);
        log.info("thread"+i+"  already.....");
        try {
            cyclicBarrier.await(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }
        finally {
            log.info("thread"+i+"  continue..........");
        }
    }
}
