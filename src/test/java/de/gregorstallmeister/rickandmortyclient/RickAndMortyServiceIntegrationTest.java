package de.gregorstallmeister.rickandmortyclient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
public class RickAndMortyServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Test
    void findAllCharactersTest() throws Exception {
        mockRestServiceServer.expect(requestTo("https://rickandmortyapi.com/api/character"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("""
                        {
                            "info": {
                                "count": 826,
                                "pages": 42,
                                "next": "https://rickandmortyapi.com/api/character?page=2",
                                "prev": null
                            },
                            "results": [
                                {
                                    "id": 1,
                                    "name": "Rick Sanchez",
                                    "status": "Alive",
                                    "species": "Human",
                                    "type": "",
                                    "gender": "Male",
                                    "origin": {
                                        "name": "Earth (C-137)",
                                        "url": "https://rickandmortyapi.com/api/location/1"
                                    },
                                    "location": {
                                        "name": "Citadel of Ricks",
                                        "url": "https://rickandmortyapi.com/api/location/3"
                                    }
                                },
                                {
                                    "id": 2,
                                    "name": "Morty Smith",
                                    "status": "Alive",
                                    "species": "Human",
                                    "type": "",
                                    "gender": "Male",
                                    "origin": {
                                        "name": "unknown",
                                        "url": ""
                                    },
                                    "location": {
                                        "name": "Citadel of Ricks",
                                        "url": "https://rickandmortyapi.com/api/location/3"
                                    }
                                },
                                {
                                    "id": 3,
                                    "name": "Summer Smith",
                                    "status": "Alive",
                                    "species": "Human",
                                    "type": "",
                                    "gender": "Female",
                                    "origin": {
                                        "name": "Earth (Replacement Dimension)",
                                        "url": "https://rickandmortyapi.com/api/location/20"
                                    }
                                }
                            ]
                      }
                      """,
                        MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders.get("/rickandmortyclient/character"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                      
                            [
                              {
                                "id":1,
                                "name":"Rick Sanchez",
                                "status":"Alive",
                                "species":"Human"
                              },
                              {
                                 "id":2,
                                 "name":"Morty Smith",
                                 "status":"Alive",
                                 "species":"Human"
                              },
                              {"id":3,"name":"Summer Smith","status":"Alive","species":"Human"}
                            ]
                      """));
    }
}
