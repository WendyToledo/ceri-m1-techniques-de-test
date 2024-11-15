package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;

public class IPokedexFactoryTest {
	
	private IPokedexFactory pokedexFacto;
	
	@BeforeEach
	public void setUp() {
		 pokedexFacto = new PokedexFactory();
	}
	
	@Test
	public void testCreatePokedex() {
		IPokemonMetadataProvider metadata = Mockito.mock(IPokemonMetadataProvider.class);
		IPokemonFactory pokemonFacto = Mockito.mock(IPokemonFactory.class);
		IPokedex pokedex = pokedexFacto.createPokedex(metadata, pokemonFacto);
		
		assertNotNull(pokedex);
		assertEquals(0, pokedex.size());
	}
	
	@Test
	public void testCreatePokedexIsCall(){
		IPokemonMetadataProvider metadata = Mockito.mock(IPokemonMetadataProvider.class);
		IPokemonFactory pokemonFacto = Mockito.mock(IPokemonFactory.class);
		verify(pokedexFacto, times(1)).createPokedex(metadata, pokemonFacto);
	}
}
