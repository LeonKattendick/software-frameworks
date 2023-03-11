package at.technikum.backend.service;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.JsonGameData;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = Constants.TOPIC_NAME, groupId = Constants.GROUP_ID)
    public void consume(ConsumerRecord<String, JsonGameData> data) {
        log.info("Received data for game {} with content {}", data.value().getGameType(), data.value().getContent());
    }
}
