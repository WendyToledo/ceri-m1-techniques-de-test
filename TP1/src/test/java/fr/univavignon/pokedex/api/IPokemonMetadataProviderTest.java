package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class IPokemonMetadataProviderTest{
	
	IPokemonMetadataProvider pokemonMetadataProvider;
	
	
	PokemonMetadata bulbi;
	
	@BeforeEach
	public void setup() {
		
		List<PokemonMetadata> list = new ArrayList<>();
		
		pokemonMetadataProvider = new PokemonMetadataProvider(list);
		
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
	
	@Test
	public void testGetPokemonMetadataInvalidIndex() throws PokedexException{
		PokedexException exception = assertThrows(PokedexException.class, () -> {
			pokemonMetadataProvider.getPokemonMetadata(-1);
        });
        assertEquals("Invalid Pokémon ID: -1", exception.getMessage());

        exception = assertThrows(PokedexException.class, () -> {
        	pokemonMetadataProvider.getPokemonMetadata(999);
        });
        assertEquals("Invalid Pokémon ID: 999", exception.getMessage());
		
	}
}
