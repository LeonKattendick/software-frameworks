package at.technikum.kafka;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;


public class KafkaHelper {
    public static Properties propertiesConfig() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-application");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");


        properties.put("schema.registry.url", "http://localhost:8081");


        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
/*

        application.id=kafka-streams-101
        bootstrap.servers=localhost:29092
        input.topic.name=random-strings
        output.topic.name=tall-random-strings
        key.serializer=org.apache.kafka.common.serialization.StringSerializer
        value.serializer=org.apache.kafka.common.serialization.StringSerializer
        *
        * */

        return properties;
    }
}
