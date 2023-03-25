package at.technikum.backend.persistence.model;

import at.technikum.commons.schema.unified.GameType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class PlayerEntity {

    @Id
    private String playerId;

    private String name;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    private double globalKda;

    @OneToMany(mappedBy = "player")
    private List<MatchEntity> matches;

}
