package com.example.kafka.comsumergroup;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

/**
 * 指定offest消费
 */
public class CustomConsumer4 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty("bootstrap.servers", "localhost:9092,localhost:9093");
        properties.setProperty("group.id", "test");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList("first", "foo"));

        //获取相关的分区信息
        Set<TopicPartition> assignment = consumer.assignment();
        while (assignment.size() == 0) {
            consumer.poll(Duration.ofMillis(1));
            assignment = consumer.assignment();
        }
        //1。从指定的offest位置之后的数据进行消费
        for (TopicPartition topicPartition : assignment) {
            consumer.seek(topicPartition, 100);
        }

        //2。从指定的时间之后的数据进行消费【获取一天前的数据】
        Map<TopicPartition, Long> timestampsToSearch = new HashMap<>();
        for (TopicPartition topicPartition : assignment) {
            timestampsToSearch.put(topicPartition,System.currentTimeMillis() - 1 * 24 * 60 * 60 * 1000);
        }
        Map<TopicPartition, OffsetAndTimestamp> topicPartitionOffsetAndTimestampMap = consumer.offsetsForTimes(timestampsToSearch);
        for (TopicPartition topicPartition : assignment) {
            consumer.seek(topicPartition, topicPartitionOffsetAndTimestampMap.get(topicPartition).offset());
        }

        ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(10));
        for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
            System.out.println(consumerRecord.toString());
        }
    }
}
