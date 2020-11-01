package com.it.ldq.kafkademo.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @Auther: ldq
 * @Date: 2020/3/12
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6379);
        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while(true) {
                long end = System.currentTimeMillis();
                if (end-start>=1000){
                    break;
                }
                i++;
                jedis.set("ld"+i,""+i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        log.info("redis每秒的操作:{}",i);
    }
}
