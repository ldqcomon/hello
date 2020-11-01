package com.it.ldq.kafkademo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.logging.Logger;


/**
 * @Auther: ldq
 * @Date: 2020/2/29
 * @Description:
 * @Version: 1.0
 */
@Slf4j
public class Consumer {
    private static final String TOPIC = "ok";
    private static final String BROKER_LIST = "192.168.43.172:9092";
    private static KafkaConsumer<String,String> consumer = null;
    static {
        Properties configs = initConfig();
        consumer = new KafkaConsumer<String, String>(configs);
    }

    private static Properties initConfig(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,BROKER_LIST);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"0");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //是否自动提交偏移量
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        //从哪里开始消费消息 earliest:无提交的offset时，从头开始消费  无提交的offset时，消费新产生的该分区下的数据
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }

    //再均衡监听器的使用
    private static Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();//用于跟踪偏移量的map
    private static class HandleRebalance implements ConsumerRebalanceListener {
        @Override
        //在重新分配partition之后和消费者开始读取消息之前被调用
        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        }

        @Override
        //在再均衡开始之前和消费者停止读取消息之后被调用。如果在这里提交偏移量，下一个接管partition的消费者就知道该从哪里开始读取了。
        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            //如果发生再均衡，要在即将失去partition所有权时提交偏移量。
            //注意：
            //（1）提交的是最近处理过的偏移量，而不是批次中还在处理的最后一个偏移量。因为partition有可能在我们还在处理消息时被撤回。
            //(2)我们要提交所有分区的偏移量，而不只是即将市区所有权的分区的偏移量。因为提交的偏移量是已经处理过的，所以不会有什么问题。
            //(3)调用commitSync方法，确保在再均衡发生之前提交偏移量
            consumer.commitSync(currentOffsets);
        }
    }

    public static void main(String[] args) {
        try {
            consumer.subscribe(Collections.singleton(TOPIC),new HandleRebalance() );
            while (true) {
                //消费者轮询向broker获取消息
                ConsumerRecords<String, String> records = consumer.poll(5000);
                for (ConsumerRecord<String, String> record : records) {
                    //在读取每条消息后，使用期望处理的下一个消息的偏移量更新map里的偏移量。下一次就从这里开始读取消息
                    String value = record.value();
                    log.info("value:{}",value);
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1, "no matadata"));
                }
                //异步提交偏移量
                consumer.commitAsync(currentOffsets, null);
            }
        }
        catch(Exception e){
            //忽略异常，正在关闭消费者
        } finally{
            try {//再关闭消费者之前,提交偏移量,确保前面的代码发生异常的情况而没有提交偏移量
                consumer.commitSync(currentOffsets);
            } finally {
                consumer.close();
            }
        }
    }
}
