package com.it.ldq.kafkademo.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: ldq
 * @Date: 2020/3/8
 * @Description:
 * @Version: 1.0
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = null;
        final AtomicInteger account;
        try {
            threadPoolExecutor = new ThreadPoolExecutor(10,10,10000, TimeUnit.SECONDS,new ArrayBlockingQueue<>(70));
            //AbortPolicy  默认的抛出异常 当队列和线程池都满了的时候
            account = new AtomicInteger(0);
            for (int i = 0; i <100 ; i++) {
                Future<?> future = threadPoolExecutor.submit(() -> {
                    long id = Thread.currentThread().getId();
                    System.out.println("线程:" + id + "进来了.............");
                    account.getAndIncrement();
                });
                if (future.isDone()) {
                    account.getAndDecrement();
                }
            }

            //无限循环
            for (; ;) {

                if (account.intValue()==100) {
                    int activeCount = threadPoolExecutor.getActiveCount();
                    System.out.println("线程池活跃数量:"+activeCount);
                    long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
                    System.out.println("完成任务的数量:"+completedTaskCount);
                    int corePoolSize = threadPoolExecutor.getCorePoolSize();
                    System.out.println("核心线程的数量:"+corePoolSize);
                    //跳出循环
                    break;
                }
                else {
                    //主线程阻塞2秒
                    Thread.sleep(2000);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //无论程序是否发生异常,都应该要关闭线程池
            threadPoolExecutor.shutdown();
        }

    }
}
