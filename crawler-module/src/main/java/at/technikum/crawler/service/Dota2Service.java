package at.technikum.crawler.service;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.dota.Dota2Match;
import at.technikum.commons.schema.dota.Dota2Player;
import at.technikum.crawler.util.RestHelper;
import at.technikum.crawler.views.Hero;
import at.technikum.crawler.views.Match;
import at.technikum.crawler.views.Player;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Slf4j
@AllArgsConstructor
@Service
public class Dota2Service {

    private Set<Hero> heroes;

    private ArrayList<Dota2Player> dota2Players;

    @EventListener(ApplicationReadyEvent.class)
    public void initialiseDotaHeroes() {
        heroes = new HashSet<>(Arrays.asList(RestHelper.getResponseEntityBody("https://api.opendota.com/api/heroes",  Hero[].class)));
        log.info("Loaded heroes = {}", heroes);
    }

    public ArrayList<Dota2Player> getDota2Data() {

        for (int dotaPlayerId : Constants.DOTA_PLAYER_IDS) {
            Player player = RestHelper.getResponseEntityBody(("https://api.opendota.com/api/players/" + dotaPlayerId), Player.class);
            log.info("Receiving Dota2Player = {}", player);

            Match[] matches = RestHelper.getResponseEntityBody(("https://api.opendota.com/api/players/" + player.getProfile().getAccountId()
                    + "/matches?limit=10"), Match[].class);
            log.info("Receiving Dota2Player matches = {}", Arrays.toString(matches));

            dota2Players.add(createDotaPlayer(player, matches));
        }

        return dota2Players;
    }

    private Dota2Player createDotaPlayer(Player player, Match[] matches) {

        ArrayList<Dota2Match> dotaMatches = new ArrayList<>();
        for (Match match : matches) {
            dotaMatches.add(
                    Dota2Match.newBuilder()
                            .setMatchId(match.getMatchId())
                            .setKills(match.getKills())
                            .setDeaths(match.getDeaths())
                            .setAssists(match.getAssists())
                            .setHeroName(matchHeroWithId(match.getHeroId()))
                            .setRadiantWin(match.isRadiantWin())
                            .build()
            );
        }

        return Dota2Player.newBuilder()
                .setAccountId(player.getProfile().getAccountId())
                .setName(player.getProfile().getName())
                .setMatches(dotaMatches)
                .build();
    }

    private String matchHeroWithId(int heroId) {
      return heroes.stream()
              .filter(v-> v.getId() == heroId)
              .map(Hero::getName)
              .findFirst()
              .orElse(null);
    }
}
