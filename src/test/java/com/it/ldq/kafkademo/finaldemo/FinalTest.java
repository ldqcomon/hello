package com.it.ldq.kafkademo.finaldemo;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Auther: ldq
 * @Date: 2020/3/11
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class FinalTest {
   //请求总数
    private static int totals = 5000;
    // 并发线程数
    private static int ThreadTotals = 200;
    //共享变量
    private static Map concurrentHashMap = new ConcurrentHashMap();

    @Test
    public void test1() throws InterruptedException {
        // 信号量
        Semaphore semaphore = new Semaphore(ThreadTotals);
        // 线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 计数器
        CountDownLatch countDownLatch = new CountDownLatch(totals);
        for (int i = 0; i < totals; i++) {
            int finalI = i;
            executorService.execute(()->{
                //获取凭证
                try {
                    semaphore.acquire();
                    concurrentHashMap.put(finalI, finalI);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("InterruptedException",e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        // 等待5000个请求都执行完成后,在停止线程池任务
        countDownLatch.await();
        executorService.shutdown();
        log.info("the size of stringBuilder is:{}",concurrentHashMap.size());
    }



}
