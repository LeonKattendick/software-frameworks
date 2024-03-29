package at.technikum.kafka;

import at.technikum.commons.schema.dota.Dota2Player;
import at.technikum.commons.schema.kda.GlobalKDA;
import at.technikum.commons.schema.kda.PlayerKDA;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import at.technikum.commons.schema.unified.UnifiedPlayer;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;


public class KafkaHelper {

    private static String kafkaUrl, schemaRegistryUrl;

    public static void init(boolean isDocker) {
        if (isDocker) {
            kafkaUrl = "kafka:29092";
            schemaRegistryUrl = "http://schema-registry:8081";
        } else {
            kafkaUrl = "localhost:9092";
            schemaRegistryUrl = "http://localhost:8081";
        }
    }

    public static Properties propertiesConfig() {

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "tracker-streams");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);

        properties.put("schema.registry.url", schemaRegistryUrl);

        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);

        return properties;
    }

    public static Map<String, String> valueProperties() {
        return Collections.singletonMap("schema.registry.url", schemaRegistryUrl);
    }

    public static Serde<Dota2Player> dotaValueSerde() {
        Serde<Dota2Player> serde = new SpecificAvroSerde<>();
        serde.configure(KafkaHelper.valueProperties(), false);

        return serde;
    }

    public static Serde<LeagueOfLegendsPlayer> leagueValueSerde() {
        Serde<LeagueOfLegendsPlayer> serde = new SpecificAvroSerde<>();
        serde.configure(KafkaHelper.valueProperties(), false);

        return serde;
    }

    public static Serde<PlayerKDA> playerKdaValueSerde() {
        Serde<PlayerKDA> serde = new SpecificAvroSerde<>();
        serde.configure(KafkaHelper.valueProperties(), false);

        return serde;
    }

    public static Serde<GlobalKDA> globalKdaValueSerde() {
        Serde<GlobalKDA> serde = new SpecificAvroSerde<>();
        serde.configure(KafkaHelper.valueProperties(), false);

        return serde;
    }

    public static Serde<UnifiedPlayer> unifiedValueSerde() {
        Serde<UnifiedPlayer> serde = new SpecificAvroSerde<>();
        serde.configure(KafkaHelper.valueProperties(), false);

        return serde;
    }
}
