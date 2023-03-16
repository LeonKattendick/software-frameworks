package at.technikum.kafka;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.dota.Dota2Match;
import at.technikum.commons.schema.dota.Dota2Player;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import at.technikum.commons.schema.unified.GameType;
import at.technikum.commons.schema.unified.UnifiedMatch;
import at.technikum.commons.schema.unified.UnifiedPlayer;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;


public class KafkaStreamProcessor {

    public static void main(String[] args) {

        // Config -- WORKING

        final StreamsBuilder builder = new StreamsBuilder();
        final Properties props = KafkaHelper.propertiesConfig(); //TODO props


        // Serdes
        final Serde<String> stringSerde = Serdes.String();

        final Serde<Dota2Player> dotaValueSerde = new SpecificAvroSerde<>();
        dotaValueSerde.configure(Collections.singletonMap("schema.registry.url",
                "http://localhost:8081"), false);

        final Serde<LeagueOfLegendsPlayer> leagueValueSerde = new SpecificAvroSerde<>();
        leagueValueSerde.configure(Collections.singletonMap("schema.registry.url",
                "http://localhost:8081"), false);

        final Serde<UnifiedPlayer> unifiedPlayerSerde = new SpecificAvroSerde<>();
        unifiedPlayerSerde.configure(Collections.singletonMap("schema.registry.url",
                "http://localhost:8081"), false);


        // Streams

        //builder.stream(Constants.TOPIC_NAME_DOTA2)                .to(Constants.TOPIC_NAME_UNIFIED);
        /*
        KStream<String, UnifiedPlayer> dotaToUnifiedPlayerStream =
                builder.stream(Constants.TOPIC_NAME_DOTA2);
        dotaToUnifiedPlayerStream.to(Constants.TOPIC_NAME_UNIFIED,
                Produced.with(stringSerde, valueSpecificAvroSerde));
         */

        KStream<String, Dota2Player> dotaToUnifiedPlayerStream =
                builder.stream(Constants.TOPIC_NAME_DOTA2,
                        Consumed.with(stringSerde, dotaValueSerde));

        dotaToUnifiedPlayerStream
                .mapValues(KafkaStreamProcessor::dota2PlayerToUnifiedPlayer)
                .to(Constants.TOPIC_NAME_UNIFIED, Produced.with(stringSerde, unifiedPlayerSerde));

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


    static UnifiedPlayer dota2PlayerToUnifiedPlayer(Dota2Player dplayer) {
        UnifiedPlayer uniplayer = UnifiedPlayer.newBuilder()
                .setGameType(GameType.DOTA2)
                .setId(dplayer.getAccountId())
                .setName(dplayer.getName())
                .setMatches(dota2MatchesToUnifiedMatches(dplayer.getMatches()))
                .build();

        return uniplayer;
    }

    static List<UnifiedMatch> dota2MatchesToUnifiedMatches(List<Dota2Match> dota2Matches) {
        List<UnifiedMatch> unifiedMatches = new ArrayList<UnifiedMatch>();
        for (Dota2Match match : dota2Matches) {
            unifiedMatches.add(dota2MatchToUnifiedMatch(match));
            ;
        }
        return unifiedMatches;
    }

    static UnifiedMatch dota2MatchToUnifiedMatch(Dota2Match dota2Match) {
        UnifiedMatch match = new UnifiedMatch();
        match.setMatchId(dota2Match.getMatchId());
        match.setKills(dota2Match.getKills());
        match.setAssists(dota2Match.getAssists());
        match.setDeaths(dota2Match.getDeaths());
        ;
        match.setWin(dota2Match.getRadiantWin());
        match.setChampionName(dota2Match.getHeroName());
        return new UnifiedMatch();
    }
}
