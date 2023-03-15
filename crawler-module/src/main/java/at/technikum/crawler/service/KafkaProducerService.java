package at.technikum.crawler.service;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.dota.Dota2Player;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class KafkaProducerService {

    private KafkaTemplate<String, Dota2Player> dota2KafkaTemplate;

    private KafkaTemplate<String, LeagueOfLegendsPlayer> leagueOfLegendsKafkaTemplate;

    public void sendDota2Message(Dota2Player data) {
        log.info("Sending Dota2 Kafka data = {}", data);
        dota2KafkaTemplate.send(Constants.TOPIC_NAME_DOTA2, data);
    }

    public void sendLeagueOfLegendsMessage(LeagueOfLegendsPlayer data) {
        log.info("Sending League Kafka data = {}", data);
        leagueOfLegendsKafkaTemplate.send(Constants.TOPIC_NAME_LEAGUE_OF_LEGENDS, data);
    }
}
