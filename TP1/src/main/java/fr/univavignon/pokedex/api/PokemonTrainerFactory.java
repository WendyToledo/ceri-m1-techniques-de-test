package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {

	@Override
	public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
		List <PokemonMetadata> list = new ArrayList<>();
		
		IPokemonMetadataProvider metadata = new PokemonMetadataProvider(list);
		IPokemonFactory facto = new PokemonFactory(metadata);
		
		IPokedex pokedex = pokedexFactory.createPokedex(metadata, facto);
		
	    return new PokemonTrainer(name, team, pokedex);
	}

}
