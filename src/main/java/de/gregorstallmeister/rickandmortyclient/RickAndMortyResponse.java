package de.gregorstallmeister.rickandmortyclient;

import java.util.List;

public record RickAndMortyResponse(
        List<RickAndMortyCharacter> results
) {
}
