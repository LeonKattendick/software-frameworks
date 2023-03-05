package at.technikum.crawler.service;

import at.technikum.commons.Constants;
import at.technikum.commons.JsonGameData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class KafkaProducerService {

    private KafkaTemplate<String, JsonGameData> kafkaTemplate;

    public void sendMessage(JsonGameData data) {
        kafkaTemplate.send(Constants.TOPIC_NAME, data);
        log.info("Sending Kafka data: " + data);
    }
}
