package at.technikum.crawler;

import at.technikum.commons.KafkaTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class Crawler {

    public static void main(String[] args) {
        log.info("Starting Kafka Producer...");

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class.getName());

        try (KafkaProducer<String, KafkaTest> producer = new KafkaProducer<>(properties)) {

            ProducerRecord<String, KafkaTest> producerRecord = new ProducerRecord<>("demo_java", 0, "1", new KafkaTest("Test String"));

            producer.send(producerRecord);
            producer.flush();

        } catch (Exception e) {
            log.error("Error starting Kafka producer", e);
        }
    }
}
