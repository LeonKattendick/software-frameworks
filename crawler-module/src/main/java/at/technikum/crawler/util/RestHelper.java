package at.technikum.crawler.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestHelper {

    public static <T> T getResponseEntityBody(String url, Class<T> clazz) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz);

        return responseEntity.getBody();
    }
}
