package at.technikum.kafka.streams;

import at.technikum.commons.Constants;
import at.technikum.kafka.KafkaHelper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;

@Slf4j
@UtilityClass
public class KDAStream {

    public void addStream(StreamsBuilder builder) {
        builder.stream(Constants.TOPIC_NAME_KDA_RAW, Consumed.with(Serdes.String(), KafkaHelper.kdaValueSerde()))
                .peek((k, v) -> log.info("[UNMAPPED] Key: {}, Value: {}", k, v))
                .to(Constants.TOPIC_NAME_KDA, Produced.with(Serdes.String(), KafkaHelper.kdaValueSerde()));
    }
}
