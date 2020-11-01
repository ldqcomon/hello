package com.it.ldq.kafkademo.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Auther: ldq
 * @Date: 2020/3/12
 * @Description:
 * @Version: 1.0
 */
@Slf4j
@Component
public class RedisClient {
    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis() {
        return jedisPool.getResource();
    }
    public void returnJedis(Jedis jedis) {
        if (null !=jedis) {
            jedis.close();
        }
    }

    public void set(String k,String v) {
        Jedis jedis = this.getJedis();
        try {
            jedis.set(k,v);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接池
            returnJedis(jedis);
        }
    }
    public String get(String k) {
        Jedis jedis = this.getJedis();
        try {
           return jedis.get(k);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接池
            returnJedis(jedis);
        }
        return null;
    }
    public boolean existKey(String k) {
        Jedis jedis = this.getJedis();
        try {
            return jedis.exists(k);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接池
            returnJedis(jedis);
        }
        return false;
    }
}
