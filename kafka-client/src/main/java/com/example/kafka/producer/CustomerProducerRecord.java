package com.example.kafka.producer;

import com.example.kafka.Partitioner.MyPartitioner;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

/**
 * 根据默认分区器发送分区
 */
public class CustomerProducerRecord {

    public static void main(String[] args) {

        String topic = "first";
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //关联自定义分区器
//        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class);
        //设置leader是否需要将消息同步到follower中，
        properties.put(ProducerConfig.ACKS_CONFIG, "1");
        //重试次数，如果达到最大值则会提出follower
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);

        //创建kafka对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        //发送数据,指定分区
        for (int i = 0; i < 500; i++) {
            int partition = new Random().nextInt(4);
            kafkaProducer.send(new ProducerRecord<>(topic, partition, "w", "value" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        System.out.println("主题:" + recordMetadata.topic() + "分区：" + recordMetadata.partition());
                    }
                }
            });
        }
        //关闭资源
        kafkaProducer.close();
    }
}
