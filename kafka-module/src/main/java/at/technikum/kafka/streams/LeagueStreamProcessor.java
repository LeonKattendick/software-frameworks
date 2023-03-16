package at.technikum.kafka.streams;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.league.LeagueOfLegendsMatch;
import at.technikum.commons.schema.league.LeagueOfLegendsMatchParticipant;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import at.technikum.commons.schema.unified.GameType;
import at.technikum.commons.schema.unified.UnifiedMatch;
import at.technikum.commons.schema.unified.UnifiedPlayer;
import at.technikum.kafka.KafkaHelper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class LeagueStreamProcessor {

    public void addStream(StreamsBuilder builder) {
        builder.stream(Constants.TOPIC_NAME_LEAGUE_OF_LEGENDS, Consumed.with(Serdes.String(), KafkaHelper.leagueValueSerde()))
                .peek((k, v) -> log.info("[UNMAPPED] Key: {}, Value: {}", k, v))
                .mapValues(LeagueStreamProcessor::leagueOfLegendsPlayerToUnifiedPlayer)
                .peek((k, v) -> log.info("[MAPPED] Key: {}, Value: {}", k, v))
                .to(Constants.TOPIC_NAME_UNIFIED, Produced.with(Serdes.String(), KafkaHelper.unifiedValueSerde()));
    }

    private UnifiedPlayer leagueOfLegendsPlayerToUnifiedPlayer(LeagueOfLegendsPlayer leagueOfLegendsPlayer) {
        return UnifiedPlayer.newBuilder()
                .setGameType(GameType.LEAGUE_OF_LEGENDS)
                .setId(leagueOfLegendsPlayer.getPlayerUuid())
                .setName(leagueOfLegendsPlayer.getGameName())
                .setMatches(leagueOfLegendsMatchesToUnifiedMatches(leagueOfLegendsPlayer))
                .build();
    }

    private List<UnifiedMatch> leagueOfLegendsMatchesToUnifiedMatches(LeagueOfLegendsPlayer leagueOfLegendsPlayer) {
        return leagueOfLegendsPlayer.getMatches().stream()
                .map((v) -> leagueOfLegendsMatchToUnifiedMatch(leagueOfLegendsPlayer.getPlayerUuid().toString(), v))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private UnifiedMatch leagueOfLegendsMatchToUnifiedMatch(CharSequence pUuid, LeagueOfLegendsMatch leagueOfLegendsMatch) {

        LeagueOfLegendsMatchParticipant participant = leagueOfLegendsMatch.getParticipants().stream()
                .filter(v -> v.getPlayerUuid().toString().contentEquals(pUuid))
                .findAny()
                .orElse(null);

        if (participant == null) return null;

        return UnifiedMatch.newBuilder()
                .setMatchId(leagueOfLegendsMatch.getMatchId())
                .setKills(participant.getKills())
                .setAssists(participant.getAssists())
                .setDeaths(participant.getDeaths())
                .setWin(participant.getWin())
                .setChampionName(participant.getChampionName())
                .build();
    }
}
