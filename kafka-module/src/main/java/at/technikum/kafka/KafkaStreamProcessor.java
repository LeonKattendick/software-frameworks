package at.technikum.kafka;

import org.apache.kafka.streams.*;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class KafkaStreamProcessor {

    public static void main(String[] args) {
        final StreamsBuilder builder = new StreamsBuilder();
        final Properties props = KafkaHelper.propertiesConfig(); //TODO props


        // TODO: streams
        builder.stream("tracker")
                .to("newstream");


        final Topology topology = builder.build();
        final KafkaStreams streams = new KafkaStreams(topology, props);
        final CountDownLatch latch = new CountDownLatch(1);
    }
}
