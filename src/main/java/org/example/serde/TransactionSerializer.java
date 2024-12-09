package org.example.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.example.event.TransactionEvent;

public class TransactionSerializer implements Serializer<TransactionEvent> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public byte[] serialize(String s, TransactionEvent transactionEvent) {
        try {
            return mapper.writeValueAsBytes(transactionEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
