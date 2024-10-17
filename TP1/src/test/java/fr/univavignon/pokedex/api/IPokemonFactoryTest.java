package fr.univavignon.pokedex.api;

import static org.mockito.ArgumentMatchers.anyInt;
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
	public void testCreatePokemon() {
		Pokemon bulbi = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
		
		assertNotNull(bulbi);
        assertEquals(0, bulbi.getIndex());
        assertEquals("Bulbizarre", bulbi.getName());
        assertEquals(126, bulbi.getAttack());
        assertEquals(126, bulbi.getDefense());
        assertEquals(90, bulbi.getStamina());
        assertEquals(613, bulbi.getCp());
        assertEquals(64, bulbi.getHp());
        assertEquals(4000, bulbi.getDust());
        assertEquals(4, bulbi.getCandy());
	}
	
	@Test(expected = PokedexException.class)
	public void testCreatePokemonWithInvalidIndex() throws PokedexException {
		 when(pokemonFactory.createPokemon(-1, anyInt(), anyInt(), anyInt(), anyInt())).thenThrow(new PokedexException("Invalid ID"));
		 pokemonFactory.createPokemon(-1, 613, 64, 4000, 4);
	}
	 
}
