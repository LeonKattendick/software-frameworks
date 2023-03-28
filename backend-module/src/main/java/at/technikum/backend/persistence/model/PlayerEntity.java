package at.technikum.backend.persistence.model;

import at.technikum.commons.schema.unified.GameType;
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
    private String playerId;

    private String name;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    private double globalKda;

    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private List<MatchEntity> matches;

}
