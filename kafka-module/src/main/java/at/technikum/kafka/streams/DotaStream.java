package at.technikum.kafka.streams;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.dota.Dota2Match;
import at.technikum.commons.schema.dota.Dota2Player;
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
import java.util.stream.Collectors;

@Slf4j
@UtilityClass
public class DotaStream {

    public void addStream(StreamsBuilder builder) {
        builder.stream(Constants.TOPIC_NAME_DOTA2, Consumed.with(Serdes.String(), KafkaHelper.dotaValueSerde()))
                .peek((k, v) -> log.info("[UNMAPPED] Key: {}, Value: {}", k, v))
                .mapValues(DotaStream::dota2PlayerToUnifiedPlayer)
                .peek((k, v) -> log.info("[MAPPED] Key: {}, Value: {}", k, v))
                .to(Constants.TOPIC_NAME_UNIFIED, Produced.with(Serdes.String(), KafkaHelper.unifiedValueSerde()));
    }

    private UnifiedPlayer dota2PlayerToUnifiedPlayer(Dota2Player dota2Player) {
        return UnifiedPlayer.newBuilder()
                .setGameType(GameType.DOTA2)
                .setId(dota2Player.getAccountId().toString())
                .setName(dota2Player.getName())
                .setMatches(dota2MatchesToUnifiedMatches(dota2Player.getMatches()))
                .build();
    }

    private List<UnifiedMatch> dota2MatchesToUnifiedMatches(List<Dota2Match> dota2Matches) {
        return dota2Matches.stream().map(DotaStream::dota2MatchToUnifiedMatch).collect(Collectors.toList());
    }

    private UnifiedMatch dota2MatchToUnifiedMatch(Dota2Match dota2Match) {
        return UnifiedMatch.newBuilder()
                .setMatchId(dota2Match.getMatchId().toString())
                .setKills(dota2Match.getKills())
                .setAssists(dota2Match.getAssists())
                .setDeaths(dota2Match.getDeaths())
                .setWin(dota2Match.getRadiantWin())
                .setChampionName(dota2Match.getHeroName())
                .build();
    }
}
