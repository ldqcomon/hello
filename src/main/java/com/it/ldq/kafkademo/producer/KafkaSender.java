package com.it.ldq.kafkademo.producer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.it.ldq.kafkademo.entity.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Auther: ldq
 * @Date: 2020/3/3
 * @Description:
 * @Version: 1.0
 */

@Component
@Slf4j
public class KafkaSender {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    private Gson gson = new GsonBuilder().create();

    public void send() {
        Company company = new Company(175, "ldq", "hello", new Date());
        log.info("message={}",gson.toJson(company));
        kafkaTemplate.send("sun",gson.toJson(company));
    }

}
