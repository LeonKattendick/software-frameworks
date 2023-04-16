package at.technikum.backend.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("match_id")
    private String matchId;

    private int kills;

    private int deaths;

    private int assists;

    @JsonProperty("champion_name")
    private String championName;

    private boolean win;

    @ManyToOne
    @JoinColumn(name = "playerId")
    @JsonIgnore
    private PlayerEntity player;

}
