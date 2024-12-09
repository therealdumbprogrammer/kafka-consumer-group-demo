package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.example.event.TransactionEvent;

import java.util.Properties;

public class BusyProducer {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.example.serde.TransactionSerializer");

        KafkaProducer<String, TransactionEvent> kafkaProducer = new KafkaProducer<>(props);

        int i = 1;
        while (true) {
            TransactionEvent event = new TransactionEvent("Id:"+i, i);
            kafkaProducer.send(new ProducerRecord<>("transactions", event.getTxnId(), event));
            i++;
            Thread.sleep(500);
        }
    }
}