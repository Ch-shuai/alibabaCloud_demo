package com.example.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomerProducer {

    public static void main(String[] args) {


        String topic = "first";
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //事务id
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "1");

        //创建kafka对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        kafkaProducer.initTransactions();
        kafkaProducer.beginTransaction();
        //发送数据
        try{
            for (int i = 0; i < 10; i++) {
                kafkaProducer.send(new ProducerRecord<>(topic, "value" + i), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (e == null) {
                            System.out.println("主题:" + recordMetadata.topic() + "分区：" + recordMetadata.partition());
                        }
                    }
                });
            }
        }catch (Exception e){
            kafkaProducer.abortTransaction();
        }finally {
            kafkaProducer.close();
        }
    }
}
