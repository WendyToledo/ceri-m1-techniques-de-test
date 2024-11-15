package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class IPokemonTrainerFactoryTest {
	
	private IPokemonTrainerFactory factory;
	private IPokedexFactory pokeFacto;
	private IPokedex poke;
	
	@BeforeEach
	public void setup() {
		
		factory = mock(IPokemonTrainerFactory.class);
		
		pokeFacto = mock(IPokedexFactory.class);
		
		poke = mock(IPokedex.class);
		when(pokeFacto.createPokedex(any(), any())).thenReturn(poke);
	}
	
	@Test
	public void testCreateTrainer() {
		String name = "Blue";
		Team team = Team.INSTINCT;
		
		PokemonTrainer trainer = new PokemonTrainer(name, team, poke);
		when(factory.createTrainer(name, team, pokeFacto)).thenReturn(trainer);	
		
		PokemonTrainer createdTrainer = factory.createTrainer(name, team, pokeFacto);
		
		assertNotNull(createdTrainer);
		assertEquals("Blue", createdTrainer.getName());
		assertEquals(Team.INSTINCT, createdTrainer.getTeam());
		assertEquals(poke, createdTrainer.getPokedex());
		
		
	}

}
