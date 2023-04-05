package at.technikum.crawler.controller;

import at.technikum.crawler.service.GameService;
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

    private GameService gameService;

    @GetMapping("")
    public ResponseEntity<String> sendGameData() {
        gameService.sendGameData();
        return ResponseEntity.ok("Sent GameData");
    }
}
