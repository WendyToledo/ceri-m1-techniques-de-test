package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class IPokedexTest{
	
	private IPokedex pokedex;
	private Pokemon aqua, bulbi;
	
	@Before
	public void setup() throws PokedexException {
		pokedex = mock(IPokedex.class);
		
		aqua = new Pokemon(133,"Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
		bulbi = new Pokemon(0,"Bulbizarre", 126, 126, 90, 613, 64, 400, 4, 56);
		
		when(pokedex.size()).thenReturn(2);
		
		when(pokedex.getPokemon(0)).thenReturn(bulbi);
        when(pokedex.getPokemon(133)).thenReturn(aqua);
        
        when(pokedex.addPokemon(bulbi)).thenReturn(0);
        when(pokedex.addPokemon(aqua)).thenReturn(1);
        
        List<Pokemon> pokemons = new ArrayList<Pokemon>();
        pokemons.add(bulbi);
        pokemons.add(aqua);
        when(pokedex.getPokemons()).thenReturn(pokemons);
        
	}
	
	@Test
	public void testSize() {
		assertEquals(2,pokedex.size());
	}
	
	@Test
	public void testAddPokemon() {
		assertEquals(0, pokedex.addPokemon(bulbi));
        assertEquals(1, pokedex.addPokemon(aqua));
	}
	
	@Test
	public void testGetPokemon() throws PokedexException {
		
		Pokemon bulbiFind = pokedex.getPokemon(0);
        assertNotNull(bulbiFind);
        assertEquals("Bulbizarre", bulbiFind.getName());
        assertEquals(613, bulbiFind.getCp());

        Pokemon aquaFind = pokedex.getPokemon(133);
        assertNotNull(aquaFind);
        assertEquals("Aquali", aquaFind.getName());
        assertEquals(2729, aquaFind.getCp());
		
	}
	
	 @Test(expected = PokedexException.class)
	    public void testGetPokemonInvalidId() throws PokedexException {
	        when(pokedex.getPokemon(-1)).thenThrow(new PokedexException("Invalid ID"));
	        pokedex.getPokemon(-1);
	    }
	 
	 @Test
		public void testGetPokemons() throws PokedexException {
			
		 	Comparator<Pokemon> comparator = Comparator.comparingInt(Pokemon::getCp);

	        List<Pokemon> sortedPokemons = new ArrayList<Pokemon>();
	        sortedPokemons.add(aqua);
	        sortedPokemons.add(bulbi);
	        
	        when(pokedex.getPokemons(comparator)).thenReturn(sortedPokemons);

	        List<Pokemon> result = pokedex.getPokemons(comparator);
	        assertNotNull(result);
	        assertEquals(2, result.size());
	        assertEquals("Aquali", result.get(0).getName());
	        assertEquals("Bulbizarre", result.get(1).getName());
		}
	
}
