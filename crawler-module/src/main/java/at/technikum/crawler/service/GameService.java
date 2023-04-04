package at.technikum.crawler.service;

import at.technikum.commons.Constants;
import at.technikum.commons.schema.dota.Dota2Player;
import at.technikum.commons.schema.kda.PlayerKDA;
import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import at.technikum.crawler.util.KdaHelper;
import at.technikum.crawler.views.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import static at.technikum.commons.Constants.*;
import java.util.ArrayList;
import java.util.Arrays;


@Slf4j
@AllArgsConstructor
@Service
public class GameService {

    private KafkaProducerService kafkaProducerService;
    private ObjectMapper mapper;

    public void getDotaData() {
        String url = "https://api.opendota.com";
        ObjectMapper objectMapper = new ObjectMapper();
        Dota2Player x = new Dota2Player();

        // ArrayList<Dota2Player> players = new ArrayList<>();

        // ProPlayer[] proPlayers = getResponseEntityBody(("https://api.opendota.com" + "/api/proPlayers"), ProPlayer[].class);
        // System.out.println(Arrays.toString(proPlayers));

        for (int i = 0; i < DOTA_PLAYER_IDS.length; i++){
            Player player = getResponseEntityBody(("https://api.opendota.com/api/players/" + DOTA_PLAYER_IDS[i]), Player.class);
            System.out.println(player);
        }
        // /api/proPlayers // Max 50
        // /api/players/{account_id}
        // /api/players/{account_id}/matches
        // /api/heroes
        // x.setName(); x.setAccountId(); x.setMatches();

    }

    public void getLeagueOfLegendsData() {

    }

    private <T> T getResponseEntityBody(String url, Class<T> clazz) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz);

        return responseEntity.getBody();
    }
}
