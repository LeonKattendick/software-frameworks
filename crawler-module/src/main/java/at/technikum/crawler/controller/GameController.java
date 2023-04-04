package at.technikum.crawler.controller;

import at.technikum.commons.schema.dota.Dota2Match;
import at.technikum.commons.schema.dota.Dota2Player;
import at.technikum.commons.schema.league.LeagueOfLegendsMatch;
import at.technikum.commons.schema.league.LeagueOfLegendsMatchParticipant;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import at.technikum.crawler.service.GameService;
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
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    @GetMapping("/dota")
    public ResponseEntity<String> getDotaData() {
        gameService.getDotaData();
        return ResponseEntity.ok("Received Dota Data");
    }

    @GetMapping("/league")
    public ResponseEntity<String> getLeagueData() {
        gameService.getLeagueOfLegendsData();
        return ResponseEntity.ok("Received League Data");
    }
}
