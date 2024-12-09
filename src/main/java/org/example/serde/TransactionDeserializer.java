package org.example.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.event.TransactionEvent;

import java.io.IOException;

public class TransactionDeserializer implements Deserializer<TransactionEvent> {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public TransactionEvent deserialize(String s, byte[] bytes) {
        try {
            return mapper.readValue(bytes, TransactionEvent.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
