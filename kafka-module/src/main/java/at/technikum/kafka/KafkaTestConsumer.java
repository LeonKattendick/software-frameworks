package at.technikum.kafka;

import at.technikum.commons.AppConstants;
import at.technikum.commons.KafkaTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Slf4j
public class KafkaTestConsumer {

    public static void main(String[] args) {
        log.info("Starting Kafka Test Consumer...");

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, AppConstants.GROUP_ID);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        try (KafkaConsumer<String, KafkaTest> consumer = new KafkaConsumer<>(properties)) {

            consumer.subscribe(Collections.singletonList(AppConstants.TOPIC_NAME));

            while (true) {
                ConsumerRecords<String, KafkaTest> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, KafkaTest> record : records) {
                    log.info("Key: " + record.key() + ", Value: " + record.value());
                    log.info("Partition: " + record.partition() + ", Offset: " + record.offset());
                }
            }
        } catch (Exception e) {
            log.error("Error starting Kafka Consumer", e);
        }
    }
}
