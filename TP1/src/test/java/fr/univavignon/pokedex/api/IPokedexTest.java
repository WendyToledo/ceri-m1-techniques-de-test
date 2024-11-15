package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class IPokedexTest {

    IPokedex pokedex;
    Pokemon aqua, bulbi;
    IPokemonMetadataProvider metadata;
    IPokemonFactory facto;

    @BeforeEach
    public void setup() {
        facto = mock(IPokemonFactory.class);
        metadata = mock(IPokemonMetadataProvider.class);

        aqua = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
        bulbi = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 400, 4, 56);

        pokedex = new Pokedex(metadata, facto);
    }

    @Test
    public void testSize() {
        assertEquals(0, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        assertEquals(0, pokedex.addPokemon(bulbi));
        assertEquals(1, pokedex.addPokemon(aqua));
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        pokedex.addPokemon(bulbi);
        pokedex.addPokemon(aqua);

        Pokemon bulbiFind = pokedex.getPokemon(0);
        assertNotNull(bulbiFind);
        assertEquals("Bulbizarre", bulbiFind.getName());
        assertEquals(613, bulbiFind.getCp());

        Pokemon aquaFind = pokedex.getPokemon(133);
        assertNotNull(aquaFind);
        assertEquals("Aquali", aquaFind.getName());
        assertEquals(2729, aquaFind.getCp());
    }

    @Test
    public void testGetPokemonInvalidId() {
        PokedexException exception = assertThrows(PokedexException.class, () -> {
            pokedex.getPokemon(-1);
        });
        assertEquals("Invalid Pokémon ID: -1", exception.getMessage());

        exception = assertThrows(PokedexException.class, () -> {
            pokedex.getPokemon(999);
        });
        assertEquals("Invalid Pokémon ID: 999", exception.getMessage());
    }

    @Test
    public void testGetPokemons() {
        pokedex.addPokemon(bulbi);
        pokedex.addPokemon(aqua);

        List<Pokemon> result = pokedex.getPokemons();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Bulbizarre", result.get(0).getName());
        assertEquals("Aquali", result.get(1).getName());
    }

    @Test
    public void testGetPokemonsSorted() {
    	pokedex.addPokemon(bulbi);
        pokedex.addPokemon(aqua);

        Comparator<Pokemon> comparator = Comparator.comparingInt(Pokemon::getCp).reversed();
        List<Pokemon> sortedPokemons = pokedex.getPokemons(comparator);

        assertNotNull(sortedPokemons);
        assertEquals(2, sortedPokemons.size());
        assertEquals("Aquali", sortedPokemons.get(0).getName());
        assertEquals("Bulbizarre", sortedPokemons.get(1).getName());
    }
}
