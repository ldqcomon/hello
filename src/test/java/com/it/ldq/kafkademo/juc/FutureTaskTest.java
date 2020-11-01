package com.it.ldq.kafkademo.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Auther: ldq
 * @Date: 2020/3/11
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class FutureTaskTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do somthing in callable.....");
                Thread.sleep(5000);
                return "done";
            }
        });
        new Thread(futureTask).start();
        Thread.sleep(1000);
        log.info("do somthing in main ..........");
        String result = futureTask.get();
        log.info("result:{}",result);
    }
}
