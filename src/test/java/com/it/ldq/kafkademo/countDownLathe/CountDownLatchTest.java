package com.it.ldq.kafkademo.countDownLathe;

import java.util.concurrent.*;

/**
 * @Auther: ldq
 * @Date: 2020/3/9
 * @Description:
 * @Version: 1.0
 */
public class CountDownLatchTest {
    static class Task implements Runnable {
        private CountDownLatch countDownLatch;
        public Task(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            long id = Thread.currentThread().getId();
            System.out.println("线程"+id+"执行了普通的任务.........");
            this.countDownLatch.countDown();
            long count = countDownLatch.getCount();
            System.out.println("当前count的值:"+count);
        }
    }

    static class EndTash implements Runnable {
        private CountDownLatch countDownLatch;
        private ThreadPoolExecutor threadPoolExecutor;
        public EndTash(CountDownLatch countDownLatch,ThreadPoolExecutor threadPoolExecutor) {
            this.countDownLatch = countDownLatch;
            this.threadPoolExecutor = threadPoolExecutor;
        }

        @Override
        public void run() {
            try {
                //该方法会一直阻塞(如果没有设置时间),一直到countDownLath的值减到0为止,才会执行下面的代码
                countDownLatch.await(10,TimeUnit.SECONDS);
                long count = countDownLatch.getCount();
                System.out.println("倒计时count:"+count+":执行另了最后的方法..........."+"总共完成的任务数量:"+threadPoolExecutor.getCompletedTaskCount());
                int activeCount = threadPoolExecutor.getActiveCount();
                System.out.println("活跃线程数量:"+activeCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = null;
        //拒绝的策略
        RejectedExecutionHandler callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        RejectedExecutionHandler abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        RejectedExecutionHandler discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        RejectedExecutionHandler discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
        try {
            /**
             * 当线程池和队列都满时,默认的策略是抛出异常,共有四种策略: 放在 ThreadPoolExecutor 的里面
             * 1.AbortPolicy  抛出异常
             * 2.CallerRunsPolicy  继续执行任务  只要线程池没有关闭
             * 3.DiscardPolicy  直接拒绝执行任务
             * 4.DiscardOldestPolicy 放弃最老的任务 执行新的任务
             *
             */
            CountDownLatch countDownLatch = new CountDownLatch(100);
            poolExecutor = new ThreadPoolExecutor(10,10,10000,
                    TimeUnit.SECONDS,new ArrayBlockingQueue<>(200),discardPolicy);
            //执行最终的任务
            poolExecutor.submit(new EndTash(countDownLatch,poolExecutor));
            //同时执行多个开始任务
            for (int i = 0; i < 100; i++) {
                poolExecutor.submit(new Task(countDownLatch));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //无论程序是否执行成功都要在此关闭线程池
            poolExecutor.shutdown();
        }
    }
}
