package at.technikum.backend.util;

import at.technikum.commons.OldJsonGameData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class JsonGameDataDeserializer implements Deserializer<OldJsonGameData> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OldJsonGameData deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), OldJsonGameData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
