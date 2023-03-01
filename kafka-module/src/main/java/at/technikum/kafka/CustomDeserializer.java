package at.technikum.kafka;

import at.technikum.commons.KafkaTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class CustomDeserializer implements Deserializer<KafkaTest> {

    @Override
    public KafkaTest deserialize(String s, byte[] bytes) {
        try {
            return new ObjectMapper().readValue(new String(bytes, StandardCharsets.UTF_8), KafkaTest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
