package com.it.ldq.kafkademo.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @Auther: ldq
 * @Date: 2020/3/11
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class ForkAndJoin extends RecursiveTask<Integer> {
    private static final int condiction = 2;
    private int start;
    private int end;

    public ForkAndJoin(int start, int end) {
        this.end = end;
        this.start = start;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool joinPool = new ForkJoinPool();
        ForkAndJoin forkAndJoin = new ForkAndJoin(1, 100);
        //提交一个任务
        ForkJoinTask<Integer> result = joinPool.submit(forkAndJoin);
        Integer integer = result.get();
        log.info("result:{}",integer);
    }
    @Override
    protected Integer compute() {
        int sum = 0;
        boolean b = end -start<=condiction;
        if (b) {
            for (int i = start; i <=end; i++) {
                sum +=i;
            }
        }
        else {
            int middle = (start+end)/2;
            //分成两个子任务
            ForkAndJoin forkAndJoin = new ForkAndJoin(start,middle);
            ForkAndJoin forkAndJoin2 = new ForkAndJoin(middle+1,end);
            //分别执行子任务
            forkAndJoin.fork();
            forkAndJoin2.fork();
            //等待任务执行结束合并其结果
            Integer join1 = forkAndJoin.join();
            Integer join2 = forkAndJoin2.join();
            sum = join1+join2;

        }
        return sum;
    }
}
