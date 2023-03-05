package at.technikum.crawler.controller;

import at.technikum.commons.GameType;
import at.technikum.commons.JsonGameData;
import at.technikum.crawler.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TestController {

    private KafkaProducerService kafkaProducerService;

    @GetMapping
    public ResponseEntity<String> test() {
        kafkaProducerService.sendMessage(
                JsonGameData.builder()
                        .gameType(GameType.LEAGUE_OF_LEGENDS)
                        .data("test")
                        .build()
        );
        return ResponseEntity.ok("Done");
    }
}
