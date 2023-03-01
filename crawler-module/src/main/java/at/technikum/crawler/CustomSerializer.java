package at.technikum.crawler;

import at.technikum.commons.KafkaTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class CustomSerializer implements Serializer<KafkaTest> {

    @Override
    public byte[] serialize(String s, KafkaTest kafkaTest) {
        try {
            return new ObjectMapper().writeValueAsBytes(kafkaTest);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
