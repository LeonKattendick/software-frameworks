package at.technikum.crawler.controller;

import at.technikum.commons.schema.GameType;
import at.technikum.commons.schema.JsonGameData;
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
        JsonGameData jsonGameData = JsonGameData.newBuilder()
                .setGameType(GameType.LEAGUE_OF_LEGENDS)
                .setContent("test")
                .build();

        kafkaProducerService.sendMessage(jsonGameData);

        return ResponseEntity.ok("Done");
    }
}
