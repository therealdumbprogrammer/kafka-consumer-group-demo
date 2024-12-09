package org.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.event.TransactionEvent;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class BalancedConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.example.serde.TransactionDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "txn-group-A");

        KafkaConsumer<String, TransactionEvent> kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.subscribe(List.of("transactions"));

        try {
            while (true) {
                kafkaConsumer
                        .poll(Duration.ofMillis(500))
                        .forEach(record -> System.out.println("Received a new event: " + record.partition() + ", " + record.key() + ", " + record.value()));
            }
        } finally {
            kafkaConsumer.close();
        }
    }
}
