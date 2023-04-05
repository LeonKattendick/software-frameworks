package at.technikum.crawler.scheduler;

import at.technikum.crawler.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class ApiScheduler {

    private GameService gameService;

    @Scheduled(fixedRate = 40000)
    public void requestGameData() {
        gameService.sendGameData();
        log.info("Requested Game Data");
    }
}
