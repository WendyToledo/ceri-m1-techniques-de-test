package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class IPokemonTrainerFactoryTest {
	
	IPokemonTrainerFactory factory;
    IPokedexFactory pokeFacto;
    IPokedex poke;
    IPokemonMetadataProvider metadataProvider;
    IPokemonFactory pokemonFactory;
	
	@BeforeEach
	public void setup() {
		
		metadataProvider = mock(IPokemonMetadataProvider.class);
	    pokemonFactory = mock(IPokemonFactory.class);
	    
	    pokeFacto = mock(IPokedexFactory.class);
        poke = mock(IPokedex.class);
        
        when(pokeFacto.createPokedex(metadataProvider, pokemonFactory)).thenReturn(poke);
        
        factory = new PokemonTrainerFactory();
        
	}	
	
	@Test
	public void testCreateTrainer() {
		String name = "Blue";
		Team team = Team.INSTINCT;
		
		assertNotNull(pokeFacto);
		assertNotNull(poke);
		
		PokemonTrainer createdTrainer = factory.createTrainer(name, team, pokeFacto);
		
		assertNotNull(createdTrainer);
		assertEquals(name, createdTrainer.getName());
		assertEquals(team, createdTrainer.getTeam());
		assertEquals(poke, createdTrainer.getPokedex());
		
		verify(pokeFacto).createPokedex(metadataProvider, pokemonFactory);
	}
}
