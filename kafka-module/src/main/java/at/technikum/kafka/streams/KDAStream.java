package at.technikum.kafka.streams;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.kda.GlobalKDA;
import at.technikum.commons.schema.kda.MatchKDA;
import at.technikum.kafka.KafkaHelper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;

@Slf4j
@UtilityClass
public class KDAStream {

    public void addStream(StreamsBuilder builder) {
        builder.stream(Constants.TOPIC_NAME_KDA_RAW, Consumed.with(Serdes.String(), KafkaHelper.playerKdaValueSerde()))
                .peek((k, v) -> log.info("[UNMAPPED] Key: {}, Value: {}", k, v))
                .groupByKey()
                .aggregate(
                        () -> new GlobalKDA(0, 0, 0),
                        (s, playerKDA, globalKDA) -> {
                            for (MatchKDA match : playerKDA.getMatches()) {
                                globalKDA.setKills(globalKDA.getKills() + match.getKills());
                                globalKDA.setDeaths(globalKDA.getDeaths() + match.getDeaths());
                                globalKDA.setAssists(globalKDA.getAssists() + match.getAssists());
                            }
                            return globalKDA;
                        },
                        Materialized.with(Serdes.String(), KafkaHelper.globalKdaValueSerde())
                )
                .toStream()
                .peek((k, v) -> log.info("[MAPPED] Key: {}, Value: {}", k, v))
                .to(Constants.TOPIC_NAME_KDA, Produced.with(Serdes.String(), KafkaHelper.globalKdaValueSerde()));
    }
}
