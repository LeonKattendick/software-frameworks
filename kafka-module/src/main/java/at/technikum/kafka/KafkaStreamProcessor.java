package at.technikum.kafka;

import at.technikum.kafka.streams.DotaStreamProcessor;
import at.technikum.kafka.streams.LeagueStreamProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;

import java.util.Properties;

@Slf4j
public class KafkaStreamProcessor {

    public static void main(String[] args) throws InterruptedException {

        Properties props = KafkaHelper.propertiesConfig();

        DotaStreamProcessor dotaStreamProcessor = new DotaStreamProcessor();
        LeagueStreamProcessor leagueStreamProcessor = new LeagueStreamProcessor();

        KafkaStreams dotaStreams = new KafkaStreams(dotaStreamProcessor.getTopology(), props);
        KafkaStreams leagueStreams = new KafkaStreams(leagueStreamProcessor.getTopology(), props);

        Thread dotaThread = new Thread(() -> {
            dotaStreams.cleanUp();
            dotaStreams.start();
        });
        dotaThread.start();

        Thread leagueThread = new Thread(() -> {
            leagueStreams.cleanUp();
            leagueStreams.start();
        });
        leagueThread.start();

        dotaThread.join();
        leagueThread.join();

    }
}
