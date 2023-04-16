package at.technikum.backend.persistence.model;

import at.technikum.commons.schema.unified.GameType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PlayerEntity {

    @Id
    @JsonProperty("player_id")
    private String playerId;

    private String name;

    @Enumerated(EnumType.STRING)
    @JsonProperty("game_type")
    private GameType gameType;

    @JsonProperty("global_kda")
    private double globalKda;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private List<MatchEntity> matches;

}
