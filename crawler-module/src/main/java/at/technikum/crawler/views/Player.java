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
public class Player {

    private Profile profile;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Profile {

        @JsonProperty("account_id")
        private String accountId;

        private String name;

    }
}
