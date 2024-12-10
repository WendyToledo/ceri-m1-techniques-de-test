package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IPokemonMetadataProviderTest {
	
	IPokemonMetadataProvider pokemonMetadataProvider;
	PokemonMetadata bulbi;
	
	@BeforeEach
	public void setup() {
		List<PokemonMetadata> list = new ArrayList<>();
		bulbi = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
		list.add(bulbi);
		pokemonMetadataProvider = new PokemonMetadataProvider(list);
	}
	
	@Test
	public void testGetPokemonMetadataBulbiIfIdEquals0() throws PokedexException {
		PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(0);
		assertNotNull(metadata);
		assertEquals(0, metadata.getIndex());
		assertEquals("Bulbizarre", metadata.getName());
		assertEquals(126, metadata.getAttack());
		assertEquals(126, metadata.getDefense());
		assertEquals(90, metadata.getStamina());
	}
	
	@Test
	public void testGetPokemonMetadataInvalidIndex() {
		PokedexException exception = assertThrows(PokedexException.class, () -> {
			pokemonMetadataProvider.getPokemonMetadata(-1);
		});
		assertEquals("Invalid Pokémon index: -1", exception.getMessage());

		exception = assertThrows(PokedexException.class, () -> {
			pokemonMetadataProvider.getPokemonMetadata(999);
		});
		assertEquals("Invalid Pokémon index: 999", exception.getMessage());
	}
}
