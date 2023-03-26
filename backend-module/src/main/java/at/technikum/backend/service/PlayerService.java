package at.technikum.backend.service;

import at.technikum.backend.persistence.model.PlayerEntity;
import at.technikum.backend.persistence.repository.PlayerRepository;
import at.technikum.commons.schema.kda.GlobalKDA;
import at.technikum.commons.schema.unified.UnifiedPlayer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@AllArgsConstructor
@Service
public class PlayerService {

    private PlayerRepository playerRepository;

    private MatchService matchService;

    public PlayerEntity saveUnifiedPlayer(UnifiedPlayer unifiedPlayer) {

        PlayerEntity player;
        val savedPlayer = playerRepository.findById(unifiedPlayer.getId().toString());

        if (savedPlayer.isPresent()) player = savedPlayer.get();
        else {
            player = PlayerEntity.builder()
                    .playerId(unifiedPlayer.getId().toString())
                    .name(unifiedPlayer.getName().toString())
                    .gameType(unifiedPlayer.getGameType())
                    .globalKda(0.0)
                    .matches(Collections.emptyList())
                    .build();
            playerRepository.save(player);
        }

        val savedMatches = matchService.saveUnifiedMatchList(player, unifiedPlayer.getMatches());
        player.setMatches(savedMatches);

        return player;
    }

    public PlayerEntity saveGlobalKda(String id, GlobalKDA kda) {

        val savedPlayer = playerRepository.findById(id);
        if (savedPlayer.isEmpty()) return null;

        PlayerEntity player = savedPlayer.get();
        double calculatedKda = (kda.getKills() + kda.getAssists()) / (double) kda.getDeaths();
        player.setGlobalKda(calculatedKda);

        return playerRepository.save(player);
    }
}
