package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;

public class IPokedexFactoryTest {
	
	@Mock
	private IPokedexFactory pokedexFacto;
	
	@Mock
    private IPokemonMetadataProvider metadata;
	
	@Mock
    private IPokemonFactory pokemonFacto;
	
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testCreatePokedex() {
		IPokedex ipokedex = mock(IPokedex.class);
		when(pokedexFacto.createPokedex(metadata, pokemonFacto)).thenReturn(ipokedex);
		
		IPokedex pokedex = pokedexFacto.createPokedex(metadata, pokemonFacto);
		
		assertNotNull(pokedex);
		assertEquals(0, pokedex.size());
	}
	
	@Test
	public void testCreatePokedexIsCall(){
		pokedexFacto.createPokedex(metadata, pokemonFacto);
		verify(pokedexFacto, times(1)).createPokedex(metadata, pokemonFacto);
	}
}
