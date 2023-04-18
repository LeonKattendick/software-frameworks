package at.technikum.crawler.controller;

import at.technikum.commons.schema.dota.Dota2Match;
import at.technikum.commons.schema.dota.Dota2Player;
import at.technikum.commons.schema.league.LeagueOfLegendsMatch;
import at.technikum.commons.schema.league.LeagueOfLegendsMatchParticipant;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import at.technikum.crawler.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TestController {

    private KafkaProducerService kafkaProducerService;

    @GetMapping("/dotaTest")
    public ResponseEntity<String> sendDotaTest() {

        Dota2Match match = Dota2Match.newBuilder()
                .setMatchId(456)
                .setKills(100)
                .setDeaths(200)
                .setAssists(50)
                .setHeroName("GYROCOPTER")
                .setRadiantWin(true)
                .build();

        Dota2Player player = Dota2Player.newBuilder()
                .setAccountId(123)
                .setName("Test")
                .setMatches(Collections.singletonList(match))
                .build();

        kafkaProducerService.sendDota2Message(player);

        return ResponseEntity.ok("Send Dota data");
    }

    @GetMapping("/leagueTest")
    public ResponseEntity<String> sendLeagueTest() {

        LeagueOfLegendsMatchParticipant participant = LeagueOfLegendsMatchParticipant.newBuilder()
                .setPlayerUuid("1234")
                .setKills(100)
                .setDeaths(200)
                .setAssists(50)
                .setChampionName("Zilean")
                .setWin(true)
                .build();

        LeagueOfLegendsMatch match = LeagueOfLegendsMatch.newBuilder()
                .setMatchId("4567")
                .setParticipants(Collections.singletonList(participant))
                .build();

        LeagueOfLegendsPlayer player = LeagueOfLegendsPlayer.newBuilder()
                .setPlayerUuid("1234")
                .setGameName("Test")
                .setMatches(Collections.singletonList(match))
                .build();

        kafkaProducerService.sendLeagueOfLegendsMessage(player);

        return ResponseEntity.ok("Send League data");
    }
}
