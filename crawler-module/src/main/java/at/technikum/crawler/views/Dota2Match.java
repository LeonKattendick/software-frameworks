package at.technikum.crawler.views;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dota2Match {

    @JsonProperty("match_id")
    private long matchId;

    @JsonProperty("radiant_win")
    private boolean radiantWin;

    private int assists;

    private int deaths;

    private int kills;

    @JsonProperty("hero_id")
    private int heroId;

}
