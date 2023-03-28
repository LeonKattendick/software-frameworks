package at.technikum.backend.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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
    @JsonIgnore
    private PlayerEntity player;

}
