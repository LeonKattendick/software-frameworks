package at.technikum.kafka;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.dota.Dota2Player;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import at.technikum.commons.schema.unified.UnifiedPlayer;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Collections;
import java.util.Properties;


public class KafkaStreamProcessor {

    public static void main(String[] args) {

        // Config -- WORKING

        final StreamsBuilder builder = new StreamsBuilder();
        final Properties props = KafkaHelper.propertiesConfig(); //TODO props


        // Serdes
        final Serde<String> stringSerde = Serdes.String();
        final Serde<Dota2Player> key1SpecificAvroSerde = new SpecificAvroSerde<>();
        key1SpecificAvroSerde.configure(Collections.singletonMap("schema.registry.url",
                "http://localhost:8081"), false);

        final Serde<LeagueOfLegendsPlayer> key2SpecificAvroSerde = new SpecificAvroSerde<>();
        key2SpecificAvroSerde.configure(Collections.singletonMap("schema.registry.url",
                "http://localhost:8081"), true);

        final Serde<UnifiedPlayer> valueSpecificAvroSerde = new SpecificAvroSerde<>();
        valueSpecificAvroSerde.configure(Collections.singletonMap("schema.registry.url",
                "http://localhost:8081"), false);


        // Streams

        //builder.stream(Constants.TOPIC_NAME_DOTA2)                .to(Constants.TOPIC_NAME_UNIFIED);

        /*
        KStream<String, UnifiedPlayer> dotaToUnifiedPlayerStream =
                builder.stream(Constants.TOPIC_NAME_DOTA2);
        dotaToUnifiedPlayerStream.to(Constants.TOPIC_NAME_UNIFIED,
                Produced.with(stringSerde, valueSpecificAvroSerde));


         */

        KStream<String, UnifiedPlayer> dotaToUnifiedPlayerStream =
                builder.stream(Constants.TOPIC_NAME_DOTA2,
                        Consumed.with(stringSerde, valueSpecificAvroSerde));
        dotaToUnifiedPlayerStream.to(Constants.TOPIC_NAME_UNIFIED,
                Produced.with(stringSerde, valueSpecificAvroSerde));

        /*

        KStream<LeagueOfLegendsPlayer, UnifiedPlayer> leagueToUnifiedPlayerStream =
                builder.stream(Constants.TOPIC_NAME_UNIFIED,
                        Consumed.with(key2SpecificAvroSerde, valueSpecificAvroSerde));

        leagueToUnifiedPlayerStream.to(Constants.TOPIC_NAME_UNIFIED,
                Produced.with(key2SpecificAvroSerde, valueSpecificAvroSerde));
        */

        // Start Stream -- WORKING

        final KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.cleanUp();
        streams.start();


        /*
        *
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                streams.close();
            }
        }));


        final Topology topology = builder.build();
        final CountDownLatch latch = new CountDownLatch(1);
        *
        * */
    }
}
