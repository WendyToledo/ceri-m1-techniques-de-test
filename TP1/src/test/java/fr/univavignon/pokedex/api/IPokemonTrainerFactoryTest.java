package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class IPokemonTrainerFactoryTest {
	
	IPokemonTrainerFactory factory;
    IPokedexFactory pokeFacto;
    IPokedex poke;
    IPokemonMetadataProvider metadata;
    IPokemonFactory facto;
	
	@BeforeEach
	public void setup() {
		
		metadata = mock(IPokemonMetadataProvider.class);
	    facto = mock(IPokemonFactory.class);
	    
	    pokeFacto = mock(IPokedexFactory.class);
        poke = mock(IPokedex.class);
        
        when(pokeFacto.createPokedex(metadata, facto)).thenReturn(poke);
        
        factory = new PokemonTrainerFactory(metadata, facto);
        
	}	
	
	@Test
	public void testCreateTrainer() {
		String name = "Blue";
		Team team = Team.INSTINCT;
		
		assertNotNull(pokeFacto);
		assertNotNull(metadata);
		assertNotNull(facto);
		
		PokemonTrainer createdTrainer = factory.createTrainer(name, team, pokeFacto);
		
		assertNotNull(createdTrainer);
		assertEquals(name, createdTrainer.getName());
		assertEquals(team, createdTrainer.getTeam());
		assertEquals(poke, createdTrainer.getPokedex());
		
		verify(pokeFacto).createPokedex(metadata, facto);
	}
}
