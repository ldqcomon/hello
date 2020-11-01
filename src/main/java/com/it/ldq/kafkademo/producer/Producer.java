package com.it.ldq.kafkademo.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.Logger;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Auther: ldq
 * @Date: 2020/2/29
 * @Description:
 * @Version: 1.0
 */
public class Producer {
    static Logger log = Logger.getLogger(Producer.class);
    private static final String TOPIC = "ok";
    //需要在window本地的磁盘上了修改host配置文件:192.168.43.172 centOS7
    private static final String BROKER_LIST = "192.168.43.172:9092";
    private static KafkaProducer<String,String> producer = null;

    //初始化生产者
    static {
        Properties configs = initConfig();
        producer = new KafkaProducer<String, String>(configs);
    }

   // 初始化配置
    private static Properties initConfig(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
        return properties;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //消息实体
        ProducerRecord<String , String> record = null;
        for (int i = 0; i < 5; i++) {
            record = new ProducerRecord<String, String>(TOPIC, "value"+(int)(10*(Math.random())));
           // Future<RecordMetadata> result = producer.send(record); 同步方式发送消息
            //发送消息(异步的方式)
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (null != e){
                        //失败
                        log.info("send error" + e.getMessage());
                    }else {
                        //成功
                        log.info("消息发送成功!!");
                        System.out.println(String.format("offset:%s,partition:%s",recordMetadata.offset(),recordMetadata.partition()));
                    }
                }
            });
        }
        producer.close();
    }
}
