package at.technikum.crawler.controller;

import at.technikum.crawler.service.Dota2Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {

    private Dota2Service dota2Service;

    @GetMapping("/dota")
    public ResponseEntity<String> getDotaData() {
        dota2Service.getDota2Data();
        return ResponseEntity.ok("Received Dota Data");
    }

    @GetMapping("/league")
    public ResponseEntity<String> getLeagueData() {
        // dota2Service.getLeagueOfLegendsData();
        return ResponseEntity.ok("Received League Data");
    }
}
