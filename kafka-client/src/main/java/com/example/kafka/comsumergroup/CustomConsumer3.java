package com.example.kafka.comsumergroup;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

/**
 * 创建一个消费者群-消费者1
 */
public class CustomConsumer3 {

    public static void main(String[] args) {

        // 为创建者设置需要消费的主题 消费组id 消费者信息设置
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092,localhost:9093");
        props.setProperty("group.id", "test");
        props.setProperty("enable.auto.commit", "true");
        props.setProperty("auto.commit.interval.ms", "1000");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //分区分配原则，以循环方式将分区分配给消费者
        props.setProperty(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.StickyAssignor");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //订阅主题
//        consumer.subscribe(Arrays.asList("first", "bar"));
        //指定主题下的分区
        Collection<TopicPartition> partitions = new ArrayList<>();
        partitions.add(new TopicPartition("first",3));
        consumer.assign(partitions);
        while (true){
            //拉取消息最长间隔时间
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());}
        }
    }
}
