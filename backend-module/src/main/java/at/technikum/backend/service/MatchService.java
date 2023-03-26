package at.technikum.backend.service;

import at.technikum.backend.persistence.model.MatchEntity;
import at.technikum.backend.persistence.model.PlayerEntity;
import at.technikum.backend.persistence.repository.MatchRepository;
import at.technikum.commons.schema.unified.UnifiedMatch;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class MatchService {

    private MatchRepository matchRepository;

    public List<MatchEntity> saveUnifiedMatchList(PlayerEntity player, List<UnifiedMatch> matches) {

        List<MatchEntity> savedMatches = new LinkedList<>();

        for (UnifiedMatch match : matches) {
            savedMatches.add(saveUnifiedMatch(player, match));
        }

        return savedMatches;
    }

    private MatchEntity saveUnifiedMatch(PlayerEntity player, UnifiedMatch unifiedMatch) {

        val savedMatch = matchRepository.findById(unifiedMatch.getMatchId().toString());
        if (savedMatch.isPresent()) return savedMatch.get();

        MatchEntity matchEntity = MatchEntity.builder()
                .matchId(unifiedMatch.getMatchId().toString())
                .player(player)
                .kills(unifiedMatch.getKills())
                .deaths(unifiedMatch.getDeaths())
                .assists(unifiedMatch.getAssists())
                .championName(unifiedMatch.getChampionName().toString())
                .win(unifiedMatch.getWin())
                .build();

        return matchRepository.save(matchEntity);
    }
}
