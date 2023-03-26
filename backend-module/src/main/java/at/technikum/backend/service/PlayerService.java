package at.technikum.backend.service;

import at.technikum.backend.persistence.model.PlayerEntity;
import at.technikum.backend.persistence.repository.PlayerRepository;
import at.technikum.commons.schema.kda.GlobalKDA;
import at.technikum.commons.schema.unified.UnifiedPlayer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlayerService {

    @NonNull
    private PlayerRepository playerRepository;

    @NonNull
    private MatchService matchService;

    /**
     * In the event a Kafka event for GlobalKDA reaches the application before the user registration, it will be cached here.
     */
    private final HashMap<String, GlobalKDA> cachedKda = new HashMap<>();

    public PlayerEntity saveUnifiedPlayer(UnifiedPlayer unifiedPlayer) {

        PlayerEntity player;
        val savedPlayer = playerRepository.findById(unifiedPlayer.getId().toString());

        if (savedPlayer.isPresent()) player = savedPlayer.get();
        else {
            player = playerRepository.save(
                    PlayerEntity.builder()
                            .playerId(unifiedPlayer.getId().toString())
                            .name(unifiedPlayer.getName().toString())
                            .gameType(unifiedPlayer.getGameType())
                            .globalKda(0.0)
                            .matches(Collections.emptyList())
                            .build()
            );
            syncCachedKda(player);
        }

        val savedMatches = matchService.saveUnifiedMatchList(player, unifiedPlayer.getMatches());
        player.setMatches(savedMatches);

        return player;
    }

    public PlayerEntity saveGlobalKda(String id, GlobalKDA kda) {

        val savedPlayer = playerRepository.findById(id);
        if (savedPlayer.isEmpty()) {
            cachedKda.put(id, kda);
            return null;
        }
        return saveGlobalKda(savedPlayer.get(), kda);
    }

    private PlayerEntity saveGlobalKda(PlayerEntity player, GlobalKDA kda) {

        double calculatedKda = (kda.getKills() + kda.getAssists()) / (double) kda.getDeaths();
        player.setGlobalKda(calculatedKda);

        return playerRepository.save(player);
    }

    private void syncCachedKda(PlayerEntity player) {
        if (!cachedKda.containsKey(player.getPlayerId())) return;

        saveGlobalKda(player, cachedKda.get(player.getPlayerId()));
        cachedKda.remove(player.getPlayerId());

    }
}
