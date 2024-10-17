package fr.univavignon.pokedex.api;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IPokemonFactoryTest {
	

	private IPokemonFactory pokemonFactory;
	
	@Before
	public void setUp() {
		pokemonFactory = mock(IPokemonFactory.class);
	}

	
	@Test
	public void testCreatePokemon() throws PokedexException {
		Pokemon bulbi = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 100.0);
		
	    when(pokemonFactory.createPokemon(eq(0), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(bulbi);
	    
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
