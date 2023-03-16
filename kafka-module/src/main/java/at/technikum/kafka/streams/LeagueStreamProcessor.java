package at.technikum.kafka.streams;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import at.technikum.commons.schema.unified.GameType;
import at.technikum.commons.schema.unified.UnifiedPlayer;
import at.technikum.kafka.KafkaHelper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Collections;

@Slf4j
public class LeagueStreamProcessor {

    @Getter
    private final Topology topology;

    public LeagueStreamProcessor() {

        StreamsBuilder builder = new StreamsBuilder();

        builder.stream(Constants.TOPIC_NAME_LEAGUE_OF_LEGENDS, Consumed.with(Serdes.String(), KafkaHelper.leagueValueSerde()))
                .peek((k, v) -> log.info("[UNMAPPED] Key: {}, Value: {}", k, v))
                .mapValues(this::leagueOfLegendsPlayerToUnifiedPlayer)
                .peek((k, v) -> log.info("[MAPPED] Key: {}, Value: {}", k, v))
                .to(Constants.TOPIC_NAME_UNIFIED, Produced.with(Serdes.String(), KafkaHelper.unifiedValueSerde()));

        this.topology = builder.build();
    }

    private UnifiedPlayer leagueOfLegendsPlayerToUnifiedPlayer(LeagueOfLegendsPlayer leagueOfLegendsPlayer) {
        return UnifiedPlayer.newBuilder()
                .setGameType(GameType.LEAGUE_OF_LEGENDS)
                .setId(leagueOfLegendsPlayer.getPlayerUuid())
                .setName(leagueOfLegendsPlayer.getGameName())
                .setMatches(Collections.emptyList())
                .build();
    }
}
