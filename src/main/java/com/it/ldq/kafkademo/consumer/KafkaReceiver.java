package com.it.ldq.kafkademo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Auther: ldq
 * @Date: 2020/3/3
 * @Description:
 * @Version: 1.0
 */
@Component
@Slf4j
public class KafkaReceiver {

    //@KafkaListener(topics = {"sun"})
    public void listen(ConsumerRecord<?,?> record) {
        Optional<?> value = Optional.ofNullable(record.value());
        if (value.isPresent()){
            Object msg = value.get();
            log.info("record:{}",record);
            log.info("msg:{}",msg);
        }

    }
}
