package guru.springframework.spring6resttemplate.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6resttemplate.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@Service
public class BeerClientImpl implements BeerClient {

    private final RestTemplateBuilder restTemplateBuilder;

    private static final String BASE_URL = "http://localhost:8080"; private static final String GET_BEER_PATH = "/api/v1/beer";

//    private static final String BASE_URL = "http://api.springframework.guru/"; private static final String GET_BEER_PATH = "/api/v1/beer";


//    http://localhost:8080/api/v1/beer
//    private static final String BASE_URL = "https://dummyjson.com";    private static final String GET_BEER_PATH = "/products";

    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<String> stringResponse =
                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH , String.class);

//        ResponseEntity<Map> mapResponse =
//                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, Map.class);

        ResponseEntity<JsonNode> jsonResponse =
                restTemplate.getForEntity(BASE_URL + GET_BEER_PATH, JsonNode.class);

        System.out.println("-------------37------------- = "  );

//        jsonResponse.getBody().findPath("content")
//                .elements().forEachRemaining(node -> {
//                    System.out.println(node.get("beerName").asText());
//                });

        jsonResponse.getBody()
                .elements().forEachRemaining(node -> {
                    System.out.println(node.get("beerName").asText());
                });

        System.out.println(stringResponse.getBody());

        return null;

    }
}