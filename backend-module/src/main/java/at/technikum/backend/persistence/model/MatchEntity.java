package at.technikum.backend.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MatchEntity {

    @Id
    private String matchId;

    private int kills;

    private int deaths;

    private int assists;

    private String championName;

    private boolean win;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private PlayerEntity player;

}
