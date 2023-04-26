package at.technikum.crawler.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestHelper {


    public static <T> T getResponseEntityBody(String url, Class<T> clazz) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.getForEntity(url, clazz);

        return responseEntity.getBody();
    }

    public static <T> T getResponseEntityBody(String url, Class<T> clazz, HttpHeaders headers) {

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, clazz);

        return responseEntity.getBody();
    }
}
