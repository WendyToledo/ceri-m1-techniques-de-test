package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory {
	
	private final IPokemonMetadataProvider metadata;
	
	public PokemonFactory(IPokemonMetadataProvider metadata) {
		this.metadata = metadata;
	}
	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		 try {
	         	PokemonMetadata pokeMeta = metadata.getPokemonMetadata(index);
        double iv = 0;
        return new Pokemon(index, pokeMeta.getName(), pokeMeta.getAttack(), pokeMeta.getDefense(), pokeMeta.getStamina(), cp, hp, dust, candy, iv);
		 } catch (PokedexException e) {
	            throw new IllegalArgumentException("Index invalide: " + index, e);
	        }
	}

}
