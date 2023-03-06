package at.technikum.backend.util;

import at.technikum.commons.JsonGameData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class JsonGameDataDeserializer implements Deserializer<JsonGameData> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JsonGameData deserialize(String s, byte[] bytes) {
        try {
            return objectMapper.readValue(new String(bytes, StandardCharsets.UTF_8), JsonGameData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
