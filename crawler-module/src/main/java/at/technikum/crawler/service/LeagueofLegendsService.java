package at.technikum.crawler.service;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.league.LeagueOfLegendsMatch;
import at.technikum.commons.schema.league.LeagueOfLegendsMatchParticipant;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import at.technikum.crawler.util.RestHelper;
import at.technikum.crawler.views.LolMatch;
import at.technikum.crawler.views.LolPlayer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Service
public class LeagueofLegendsService {

    @NonNull
    private ArrayList<LeagueOfLegendsPlayer> leagueOfLegendsPlayers;

    @Value("${lol.apiKey}")
    private String apiKey;

    public ArrayList<LeagueOfLegendsPlayer> getLolPlayers() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Riot-Token", apiKey);

        for (String lolPlayerId : Constants.LOL_PLAYER_IDS){
            LolPlayer player = RestHelper.getResponseEntityBody(("https://europe.api.riotgames.com/riot/account/v1/accounts/by-puuid/"
                    + lolPlayerId), LolPlayer.class, headers);
            log.info("Receiving LolPlayer = {}", player);

            String[] matchIds = (RestHelper.getResponseEntityBody(("https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/"
                    + player.getPlayerUuid()) + "/ids", String[].class, headers));

            ArrayList<LolMatch> matches = new ArrayList<>();
            for (String matchId : matchIds){
                matches.add(RestHelper.getResponseEntityBody(("https://europe.api.riotgames.com/lol/match/v5/matches/"
                        + matchId), LolMatch.class, headers));
            }

            leagueOfLegendsPlayers.add(createLeagueOfLegendsPlayer(player, matches));

        }
        return leagueOfLegendsPlayers;
    }

    private LeagueOfLegendsPlayer createLeagueOfLegendsPlayer(LolPlayer player, ArrayList<LolMatch> matches) {

        ArrayList<LeagueOfLegendsMatch> leagueOfLegendsMatches = new ArrayList<>();

        for (LolMatch match : matches) {

            leagueOfLegendsMatches.add(
                   LeagueOfLegendsMatch.newBuilder()
                            .setMatchId(match.getMetaData().getMatchId())
                            .setParticipants(createLeagueOfLegendsMatchParticipants(match))
                            .build()
            );
        }

      return LeagueOfLegendsPlayer.newBuilder()
              .setPlayerUuid(player.getPlayerUuid())
              .setMatches(leagueOfLegendsMatches)
              .setGameName("LOL")
              .build();
    }

    private ArrayList<LeagueOfLegendsMatchParticipant> createLeagueOfLegendsMatchParticipants(LolMatch match) {

        ArrayList<LeagueOfLegendsMatchParticipant> leagueOfLegendsMatchParticipants = new ArrayList<>();

        for (LolMatch.Info.Participant participant :  match.getInfo().getParticipants()){

            leagueOfLegendsMatchParticipants.add(
                    LeagueOfLegendsMatchParticipant.newBuilder()
                            .setPlayerUuid(participant.getPlayerUuid())
                            .setAssists(participant.getAssists())
                            .setDeaths(participant.getDeaths())
                            .setKills(participant.getKills())
                            .setChampionName(participant.getChampionName())
                            .setWin(participant.isWin())
                            .build()
            );
        }

        return leagueOfLegendsMatchParticipants;

    }

}
