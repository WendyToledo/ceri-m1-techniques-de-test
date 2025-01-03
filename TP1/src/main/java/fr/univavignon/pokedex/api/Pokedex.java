package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Pokedex implements IPokedex{
	
	private final IPokemonMetadataProvider metadataProvider;
    private final IPokemonFactory pokemonFactory;
    private final List<Pokemon> pokemons;

    public Pokedex(IPokemonMetadataProvider imetadataProvider, IPokemonFactory ipokemonFactory) {
        this.metadataProvider = imetadataProvider;
        this.pokemonFactory = ipokemonFactory;
        this.pokemons = new ArrayList<>();
    }
	 
	@Override
	public int size() {
		return this.pokemons.size();
	}

	@Override
	public int addPokemon(Pokemon pokemon) {
		if (pokemon == null) {
            throw new IllegalArgumentException("Pokemon invalide");
        }
        this.pokemons.add(pokemon);
        return this.pokemons.size() - 1;
	}

	@Override
	public Pokemon getPokemon(int id) throws PokedexException {
		return this.pokemons.stream().filter(pokemon -> pokemon.getIndex() == id).findFirst().orElseThrow(() -> new PokedexException("Invalid Pokémon ID: " + id));
	}

	@Override
	public List<Pokemon> getPokemons() {
		return Collections.unmodifiableList(this.pokemons);
	}

	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
		List<Pokemon> pokemonsList = new ArrayList<>(pokemons);
	    pokemonsList.sort(order);
	    return pokemonsList;
	}
	
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		return metadataProvider.getPokemonMetadata(index);
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
	}
}
