package at.technikum.crawler.util;

import at.technikum.commons.schema.dota.Dota2Match;
import at.technikum.commons.schema.dota.Dota2Player;
import at.technikum.commons.schema.kda.MatchKDA;
import at.technikum.commons.schema.kda.PlayerKDA;
import at.technikum.commons.schema.league.LeagueOfLegendsMatch;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class KdaHelper {

    public PlayerKDA getKdaFromDota2Message(Dota2Player data) {

        List<MatchKDA> matchKdaList = new ArrayList<>();

        for (Dota2Match match : data.getMatches()) {
            matchKdaList.add(MatchKDA.newBuilder()
                    .setMatchId(match.getMatchId().toString())
                    .setKills(match.getKills())
                    .setDeaths(match.getDeaths())
                    .setAssists(match.getAssists())
                    .build());
        }

        return PlayerKDA.newBuilder()
                .setPlayerId(data.getAccountId().toString())
                .setMatches(matchKdaList)
                .build();
    }

    public PlayerKDA getKdaFromLeagueOfLegendsMessage(LeagueOfLegendsPlayer data) {

        List<MatchKDA> matchKdaList = new ArrayList<>();
        val participants = data.getMatches().stream()
                .flatMap(v -> v.getParticipants().stream())
                .filter(v -> v.getPlayerUuid().equals(data.getPlayerUuid()))
                .collect(Collectors.toList());


        for (LeagueOfLegendsMatch match : data.getMatches()) {
            val participant = match.getParticipants().stream()
                    .filter(v -> v.getPlayerUuid().equals(data.getPlayerUuid()))
                    .findAny().orElse(null);

            if (participant == null) continue;

            matchKdaList.add(MatchKDA.newBuilder()
                    .setMatchId(match.getMatchId().toString())
                    .setKills(participant.getKills())
                    .setDeaths(participant.getDeaths())
                    .setAssists(participant.getAssists())
                    .build());
        }

        return PlayerKDA.newBuilder()
                .setPlayerId(data.getPlayerUuid())
                .setMatches(matchKdaList)
                .build();
    }
}
