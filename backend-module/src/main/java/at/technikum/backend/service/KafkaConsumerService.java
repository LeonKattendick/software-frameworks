package at.technikum.backend.service;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.kda.GlobalKDA;
import at.technikum.commons.schema.unified.UnifiedPlayer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class KafkaConsumerService {

    private PlayerService playerService;

    @KafkaListener(topics = Constants.TOPIC_NAME_UNIFIED, groupId = Constants.GROUP_ID)
    public void consumePlayer(ConsumerRecord<String, UnifiedPlayer> data) {
        log.info("Received data for game {} with name {}", data.value().getGameType(), data.value().getName());
        playerService.saveUnifiedPlayer(data.value());
    }

    @KafkaListener(topics = Constants.TOPIC_NAME_KDA, groupId = Constants.GROUP_ID)
    public void consumeKda(ConsumerRecord<String, GlobalKDA> data) {
        log.info("Received KDA data {} for id {}", data.value(), data.key());
        playerService.saveGlobalKda(data.key(), data.value());
    }
}
