package at.technikum.crawler.service;

import at.technikum.commons.schema.dota.Dota2Match;
import at.technikum.commons.schema.dota.Dota2Player;
import at.technikum.crawler.views.Hero;
import at.technikum.crawler.views.Match;
import at.technikum.crawler.views.Player;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

import static at.technikum.commons.Constants.DOTA_PLAYER_IDS;


@Slf4j
@AllArgsConstructor
@Service
public class Dota2Service {

    private Hero[] heroes;
    private ArrayList<Dota2Player> dota2Players;

    @EventListener(ApplicationReadyEvent.class)
    public void initialiseDotaHeroes() {
        heroes = getResponseEntityBody("https://api.opendota.com/api/heroes", Hero[].class);
        System.out.println(Arrays.toString(heroes));
    }

    public ArrayList<Dota2Player> getDota2Data() {

        for (int dotaPlayerId : DOTA_PLAYER_IDS) {
            Player player = getResponseEntityBody(("https://api.opendota.com/api/players/" + dotaPlayerId), Player.class);

            log.info("Receiving Dota2Player = {}", player);
            Match[] matches = getResponseEntityBody(("https://api.opendota.com/api/players/" + player.getProfile().getAccountId()
                    + "/matches?limit=10"), Match[].class);

            log.info("Receiving Dota2Player matches = {}", Arrays.toString(matches));

            dota2Players.add(createDotaPlayer(player, matches));
        }

        return dota2Players;
    }

    private <T> T getResponseEntityBody(String url, Class<T> clazz) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz);

        return responseEntity.getBody();
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
                            .setHeroName(matchHeroWithId(match.getHeroId(), heroes))
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

    private String matchHeroWithId(int heroId, Hero[] heroes) {

        for (Hero hero : heroes) {
            if (hero.getId() == heroId) {
                return hero.getName();
            }
        }
        return null;
    }
}
