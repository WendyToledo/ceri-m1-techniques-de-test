package fr.univavignon.pokedex.api;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class IPokemonFactoryTest {
    
    IPokemonFactory pokemonFactory;  
    IPokemonFactory rocketPokemonFactory;
    IPokemonMetadataProvider metadata;
    Pokemon bulbi;
    
    @BeforeEach
    public void setUp() {
        metadata = mock(IPokemonMetadataProvider.class);
        bulbi = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 400, 4, 56);
        pokemonFactory = new PokemonFactory(metadata);
        rocketPokemonFactory = new RocketPokemonFactory();
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
    
    @Test
    public void testRocketCreatePokemon() throws PokedexException {
        Pokemon poke = rocketPokemonFactory.createPokemon(1, 613, 64, 4000, 4);
        
        assertNotNull(poke);
        assertEquals(1, poke.getIndex());
        assertEquals("Bulbasaur", poke.getName());
        assertEquals(613, poke.getCp());
        assertEquals(64, poke.getHp());
        assertEquals(4000, poke.getDust());
        assertEquals(4, poke.getCandy());
        assertEquals(1, poke.getIv());
    }
    
    @Test
    public void testCreatePokemonInvalidId() {
    	IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(-1, 613, 64, 4000, 4);
        });
        assertEquals("Index invalide: -1", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            pokemonFactory.createPokemon(999, 613, 64, 4000, 4);
            
        });
        assertEquals("Index invalide: 999", exception.getMessage());
    }
    
    @Test
    public void testRocketCreatePokemonInvalidId() {
    	Pokemon poke = rocketPokemonFactory.createPokemon(48, 613, 64, 4000, 4);
    	
    	assertNotNull(poke);
        assertEquals(48, poke.getIndex());
        assertEquals("MISSINGNO", poke.getName());
        assertEquals(613, poke.getCp());
        assertEquals(64, poke.getHp());
        assertEquals(4000, poke.getDust());
        assertEquals(4, poke.getCandy());
        assertEquals(1, poke.getIv());
    	
    }
}
