package at.technikum.crawler.util;

import at.technikum.commons.schema.dota.Dota2Match;
import at.technikum.commons.schema.dota.Dota2Player;
import at.technikum.commons.schema.league.LeagueOfLegendsMatchParticipant;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import at.technikum.commons.schema.unified.KDA;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.stream.Collectors;

@UtilityClass
public class KdaHelper {

    public KDA getKdaFromDota2Message(Dota2Player data) {

        int kills = 0, deaths = 0, assists = 0;

        for (Dota2Match match : data.getMatches()) {
            kills += match.getKills();
            deaths += match.getDeaths();
            assists += match.getAssists();
        }

        return KDA.newBuilder()
                .setPlayerId(data.getAccountId().toString())
                .setKills(kills)
                .setDeaths(deaths)
                .setAssists(assists)
                .build();
    }

    public KDA getKdaFromLeagueOfLegendsMessage(LeagueOfLegendsPlayer data) {

        int kills = 0, deaths = 0, assists = 0;
        val participants = data.getMatches().stream()
                .flatMap(v -> v.getParticipants().stream())
                .filter(v -> v.getPlayerUuid().equals(data.getPlayerUuid()))
                .collect(Collectors.toList());

        for (LeagueOfLegendsMatchParticipant participant : participants) {
            kills += participant.getKills();
            deaths += participant.getDeaths();
            assists += participant.getAssists();
        }

        return KDA.newBuilder()
                .setPlayerId(data.getPlayerUuid())
                .setKills(kills)
                .setDeaths(deaths)
                .setAssists(assists)
                .build();
    }
}
