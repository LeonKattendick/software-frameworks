package at.technikum.crawler.service;

import at.technikum.commons.schema.league.LeagueOfLegendsPlayer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@AllArgsConstructor
@Service
public class GameService {

    private KafkaProducerService kafkaProducerService;
    private Dota2Service dota2Service;
    private LeagueofLegendsService leagueofLegendsService;

    public void sendGameData() {

       /* ArrayList<Dota2Player> dota2Players = new ArrayList<>();
        dota2Players.addAll(dota2Service.getDota2Data());

        for (Dota2Player dota2Player : dota2Players){
            kafkaProducerService.sendDota2Message(dota2Player);
        } */

        ArrayList<LeagueOfLegendsPlayer> leagueOfLegendsPlayers = new ArrayList<>();
        leagueOfLegendsPlayers.addAll(leagueofLegendsService.getLolPlayers());

        for (LeagueOfLegendsPlayer leagueOfLegendsPlayer : leagueOfLegendsPlayers){
            kafkaProducerService.sendLeagueOfLegendsMessage(leagueOfLegendsPlayer);
        }

    }
}
