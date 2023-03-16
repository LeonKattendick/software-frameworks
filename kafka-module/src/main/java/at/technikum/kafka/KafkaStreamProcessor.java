package at.technikum.kafka;

import at.technikum.kafka.streams.DotaStreamProcessor;
import at.technikum.kafka.streams.LeagueStreamProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;

import java.util.Properties;

@Slf4j
public class KafkaStreamProcessor {

    public static void main(String[] args) {

        StreamsBuilder builder = new StreamsBuilder();
        Properties props = KafkaHelper.propertiesConfig();

        DotaStreamProcessor.addStream(builder);
        LeagueStreamProcessor.addStream(builder);

        KafkaStreams streams = new KafkaStreams(builder.build(), props);

        streams.cleanUp();
        streams.start();

    }
}
