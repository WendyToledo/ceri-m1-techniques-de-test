package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;

import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class IPokedexFactoryTest {
	
	@Mock
	private IPokemonMetadataProvider metadata;
	
	@Mock
	private IPokemonFactory pokemonFacto;
	
	private IPokedexFactory pokedexFacto;
	
	@Before
	public void setUp() {
		 MockitoAnnotations.initMocks(this);
		
		pokedexFacto = (provider, factory)-> new IPokedex() {
			 @Override
            public int size() {
                return 0;
            }

            @Override
            public int addPokemon(Pokemon pokemon) {
                return 0;
            }

            @Override
            public Pokemon getPokemon(int id) throws PokedexException {
                if (id < 0 || id >150) throw new PokedexException("Invalid ID");
                return factory.createPokemon(id, 613, 64, 4000, 4);
            }

            @Override
            public List<Pokemon> getPokemons() {
                return List.of();
            }

            @Override
            public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
                return List.of(); 
            }

            @Override
            public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
                return provider.getPokemonMetadata(index);
            }

            @Override
            public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
                return factory.createPokemon(index, cp, hp, dust, candy);
            }
		};
	}
	
	@Test
	public void testCreatePokedexBulbizarre() {
		IPokedex pokedex = pokedexFacto.createPokedex(metadata, pokemonFacto);
		
		assertNotNull(pokedex);
		assertEquals(0, pokedex.size());
	}
	
	@Test
	public void testCreatePokedexIsCall() throws PokedexException{
		when(pokedexFacto.createPokedex(metadata, pokemonFacto));
		
		verify(pokedexFacto, times(1)).createPokedex(metadata, pokemonFacto);
	}
}
