package at.technikum.crawler.views;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LolMatchDto {

    private MetaData metadata;

    private Info info;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MetaData {

        private String matchId;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Info {

        private ArrayList<Participant> participants = new ArrayList<>();

        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Participant{

            private String championName;

            @JsonProperty("puuid")
            private String playerUuid;

            private int assists;

            private int deaths;

            private int kills;

            @JsonProperty("win")
            private boolean isWin;

        }

    }

}
