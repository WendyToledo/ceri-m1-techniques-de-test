package fr.univavignon.pokedex.api;

import java.util.Collections;
import java.util.List;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {
	
	 private final List<PokemonMetadata> pokemonMetadataList;
	 
	 public PokemonMetadataProvider(List<PokemonMetadata> pokemonMetadataList) {
	        this.pokemonMetadataList = Collections.unmodifiableList(pokemonMetadataList);
	    }

	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		 if (index < 0 || index >= pokemonMetadataList.size()) {
	            throw new PokedexException("Invalid Pokémon index: " + index);
	        }
	        return this.pokemonMetadataList.get(index);
	}

}
