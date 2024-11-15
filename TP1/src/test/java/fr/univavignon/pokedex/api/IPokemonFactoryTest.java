package fr.univavignon.pokedex.api;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class IPokemonFactoryTest {
    
    IPokemonFactory pokemonFactory;
    IPokemonMetadataProvider metadata;
    Pokemon bulbi;
    
    @BeforeEach
    public void setUp() {
        metadata = mock(IPokemonMetadataProvider.class);
        bulbi = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 400, 4, 56);
        pokemonFactory = new PokemonFactory(metadata);
    }

    @Test
    public void testCreatePokemon() throws PokedexException {
        when(metadata.getPokemonMetadata(eq(0))).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        Pokemon poke = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        
        assertNotNull(poke);
        assertEquals(0, poke.getIndex());
        assertEquals("Bulbizarre", poke.getName());
        assertEquals(126, poke.getAttack());
        assertEquals(126, poke.getDefense());
        assertEquals(90, poke.getStamina());
        assertEquals(613, poke.getCp());
        assertEquals(64, poke.getHp());
        assertEquals(4000, poke.getDust());
        assertEquals(4, poke.getCandy());
    }
}
