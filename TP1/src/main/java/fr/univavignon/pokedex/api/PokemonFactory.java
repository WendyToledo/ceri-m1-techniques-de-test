package fr.univavignon.pokedex.api;

public class PokemonFactory implements IPokemonFactory {
	
	private final IPokemonMetadataProvider iMetadata;
	
	public PokemonFactory(IPokemonMetadataProvider metadata) {
		iMetadata = metadata;
	}
	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		 try {
	         	PokemonMetadata pokeMeta = iMetadata.getPokemonMetadata(index);
		int attack = (int) (Math.random() * 15);
        int defense = (int) (Math.random() * 15);
        int stamina = (int) (Math.random() * 15);
        double iv = 0;
        return new Pokemon(index, pokeMeta.getName(), attack, defense, stamina, cp, hp, dust, candy, iv);
		 } catch (PokedexException e) {
	            throw new IllegalArgumentException("Index invalide: " + index, e);
	        }
	}

}
