package at.technikum.crawler.service;

import at.technikum.commons.Constants;
import at.technikum.crawler.util.RestHelper;
import at.technikum.crawler.views.Dota2Hero;
import at.technikum.crawler.views.Dota2Match;
import at.technikum.crawler.views.Dota2Player;
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

    private Set<Dota2Hero> heroes;

    private ArrayList<at.technikum.commons.schema.dota.Dota2Player> dota2Players;

    @EventListener(ApplicationReadyEvent.class)
    public void initialiseDotaHeroes() {
        heroes = new HashSet<>(Arrays.asList(RestHelper.getResponseEntityBody("https://api.opendota.com/api/heroes",  Dota2Hero[].class)));
        log.info("Loaded heroes = {}", heroes);
    }

    public ArrayList<at.technikum.commons.schema.dota.Dota2Player> getDota2Data() {

        for (int dotaPlayerId : Constants.DOTA_PLAYER_IDS) {
            Dota2Player player = RestHelper.getResponseEntityBody(("https://api.opendota.com/api/players/" + dotaPlayerId), Dota2Player.class);
            log.info("Receiving Dota2Player = {}", player);

            Dota2Match[] matches = RestHelper.getResponseEntityBody(("https://api.opendota.com/api/players/" + player.getProfile().getAccountId()
                    + "/matches?limit=10"), Dota2Match[].class);
            log.info("Receiving Dota2Player matches = {}", Arrays.toString(matches));

            dota2Players.add(createDotaPlayer(player, matches));
        }

        return dota2Players;
    }

    private at.technikum.commons.schema.dota.Dota2Player createDotaPlayer(Dota2Player player, Dota2Match[] matches) {

        ArrayList<at.technikum.commons.schema.dota.Dota2Match> dotaMatches = new ArrayList<>();
        for (Dota2Match match : matches) {
            dotaMatches.add(
                    at.technikum.commons.schema.dota.Dota2Match.newBuilder()
                            .setMatchId(match.getMatchId())
                            .setKills(match.getKills())
                            .setDeaths(match.getDeaths())
                            .setAssists(match.getAssists())
                            .setHeroName(matchHeroWithId(match.getHeroId()))
                            .setRadiantWin(match.isRadiantWin())
                            .build()
            );
        }

        return at.technikum.commons.schema.dota.Dota2Player.newBuilder()
                .setAccountId(player.getProfile().getAccountId())
                .setName(player.getProfile().getName())
                .setMatches(dotaMatches)
                .build();
    }

    private String matchHeroWithId(int heroId) {
      return heroes.stream()
              .filter(v-> v.getId() == heroId)
              .map(Dota2Hero::getName)
              .findFirst()
              .orElse(null);
    }
}
