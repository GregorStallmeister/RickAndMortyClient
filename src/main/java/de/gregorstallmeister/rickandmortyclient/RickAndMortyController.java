package de.gregorstallmeister.rickandmortyclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rickandmortyclient")
@RequiredArgsConstructor
public class RickAndMortyController {

    private final RickAndMortyService rickAndMortyService;

    @GetMapping("/character")
    public List<RickAndMortyCharacter> getCharacters() {
        return rickAndMortyService.findAllCharcters();
    }
}
