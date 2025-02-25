package de.gregorstallmeister.rickandmortyclient;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RickAndMortyService {

    private RestClient restClient = RestClient.builder().baseUrl("https://rickandmortyapi.com/api").build();

    public List<RickAndMortyCharacter> findAllCharcters() {

        String response = restClient.get().uri("/character").retrieve().body(String.class);
        System.out.println("RickAndMortyResponse raw:");
        System.out.println(response);

        RickAndMortyResponse rickAndMortyResponse = restClient.get().uri("/character")
                .retrieve().body(RickAndMortyResponse.class);
        System.out.println("RickAndMortyResponse extract results as List<RickAndMortyCharacter>:");
        System.out.println(rickAndMortyResponse);

        return rickAndMortyResponse.results();
    }
}
