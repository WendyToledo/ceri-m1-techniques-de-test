package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class IPokemonMetadataProviderTest{
	
	private IPokemonMetadataProvider pokemonMetadataProvider;
	
	private PokemonMetadata bulbi;
	private PokemonMetadata aqua;
	
	@Before
	public void setup() {
		bulbi = new PokemonMetadata(0,"Bulbizarre", 126, 126, 90);
		aqua = new PokemonMetadata(133,"Aquali", 186, 168, 260);
	}
	
	@Test
	public void testGetPokemonMetadataBulbiIf0() throws PokedexException{
		when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(bulbi);
		
		PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(0);
		
		assertNotNull(metadata);
		assertEquals(0, metadata.getIndex());
		assertEquals("Bulbizarre", metadata.getName());
		assertEquals(126, metadata.getAttack());
		assertEquals(126, metadata.getDefense());
		assertEquals(90, metadata.getStamina());
	}
	
	@Test
	public void testGetPokemonMetadataIsCall() throws PokedexException{
		when(pokemonMetadataProvider.getPokemonMetadata(133)).thenReturn(aqua);
		
		verify(pokemonMetadataProvider, times(1)).getPokemonMetadata(133);
	}
	
	@Test(expected = PokedexException.class)
	public void testGetPokemonMetadataInvalidIndex() throws PokedexException{
		when(pokemonMetadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid ID"));
		pokemonMetadataProvider.getPokemonMetadata(-1);
		
	}
}
