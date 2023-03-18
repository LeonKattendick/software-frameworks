package at.technikum.kafka;

import at.technikum.kafka.streams.DotaStream;
import at.technikum.kafka.streams.KDAStream;
import at.technikum.kafka.streams.LeagueStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;

import java.util.Properties;

@Slf4j
public class KafkaStreamProcessor {

    public static void main(String[] args) {
        KafkaHelper.init(args.length == 0);

        StreamsBuilder builder = new StreamsBuilder();
        Properties props = KafkaHelper.propertiesConfig();

        DotaStream.addStream(builder);
        LeagueStream.addStream(builder);
        KDAStream.addStream(builder);

        KafkaStreams streams = new KafkaStreams(builder.build(), props);

        streams.cleanUp();
        streams.start();

    }
}
