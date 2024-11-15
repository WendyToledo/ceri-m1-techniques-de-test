package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IPokedexFactoryTest {

    private IPokedexFactory pokedexFacto;
    private IPokemonMetadataProvider metadata;
    private IPokemonFactory pokemonFacto;
    private IPokedex pokedex;

    @BeforeEach
    public void setup() {
        metadata = mock(IPokemonMetadataProvider.class);
        pokemonFacto = mock(IPokemonFactory.class);

        pokedexFacto = new PokedexFactory();

        pokedex = pokedexFacto.createPokedex(metadata, pokemonFacto);
    }

    @Test
    public void testCreatePokedex() {
        assertNotNull(pokedex);
        assertEquals(0, pokedex.size());
    }

    @Test
    public void testCreatePokedexIsCall() throws PokedexException {
        pokedexFacto.createPokedex(metadata, pokemonFacto);
        verify(metadata, times(1)).getPokemonMetadata(anyInt());
        verify(pokemonFacto, times(1)).createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt());
    }
}
