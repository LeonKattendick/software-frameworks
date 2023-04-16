package at.technikum.backend.controller;

import at.technikum.backend.persistence.model.PlayerEntity;
import at.technikum.backend.service.PlayerService;
import at.technikum.commons.schema.unified.GameType;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/player")
public class PlayerController {

    private PlayerService playerService;

    @GetMapping("/{id}")
    public ResponseEntity<PlayerEntity> getPlayerById(@PathVariable @Validated String id) {

        val optionalPlayer = playerService.getPlayerById(id);
        if (optionalPlayer.isEmpty()) throw new EntityNotFoundException("Spieler existiert nicht");

        return ResponseEntity.ok(optionalPlayer.get());
    }

    @GetMapping
    public ResponseEntity<List<PlayerEntity>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/game/{type}")
    public ResponseEntity<List<PlayerEntity>> getAllPlayersByGame(@PathVariable @Validated GameType type) {
        return ResponseEntity.ok(playerService.getAllPlayersByGameType(type));
    }
}
