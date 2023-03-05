package at.technikum.crawler.controller;

import at.technikum.crawler.service.KafkaProducerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void test() {
        kafkaProducerService.sendMessage("Test Test");
    }
}
