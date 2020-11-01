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
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new Mycallnable());
        Thread.sleep(1000);
        log.info("do somthing in main.....");
        String result = future.get();
        log.info("result:{}",result);
    }

    static class Mycallnable implements Callable  {

        @Override
        public String call() throws Exception {
            log.info("do somthing in callnable...........");
            Thread.sleep(5000);
            return "done";
        }
    }
}
