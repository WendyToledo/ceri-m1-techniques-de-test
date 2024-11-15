package fr.univavignon.pokedex.api;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {

	@Override
	public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
		IPokemonMetadataProvider metadata = new PokemonMetadataProvider(null);
		IPokemonFactory facto = new PokemonFactory(metadata);
		 IPokedex pokedex = pokedexFactory.createPokedex(metadata, facto);
	     return new PokemonTrainer(name, team, pokedex);
	}

}
