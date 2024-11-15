package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class IPokemonMetadataProviderTest{
	
	private IPokemonMetadataProvider pokemonMetadataProvider;
	
	private PokemonMetadata bulbi;
	
	@Before
	public void setup() {
		
		pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
		
		bulbi = new PokemonMetadata(0,"Bulbizarre", 126, 126, 90);
	}
	
	@Test
	public void testGetPokemonMetadataBulbiIfIdEquals0() throws PokedexException{
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
		when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(bulbi);
		
		pokemonMetadataProvider.getPokemonMetadata(0);
		
		verify(pokemonMetadataProvider, times(1)).getPokemonMetadata(0);
	}
	
	@Test(expected = PokedexException.class)
	public void testGetPokemonMetadataInvalidIndex() throws PokedexException{
		when(pokemonMetadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid ID"));
		pokemonMetadataProvider.getPokemonMetadata(-1);
		
	}
}
