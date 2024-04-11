package de.bredex.pokedex;

import java.util.ArrayList;
import java.util.List;

import de.bredex.pokemon.Pokemon;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

@Entity
public class Pokedex {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToMany(mappedBy = "pokedex")
	@OrderBy("id ASC")
	private List<Pokemon> pokemons;

	public Pokedex() {
		
	}
	
	public Pokedex(int id, List<Pokemon> pokemons) {
		this.id = id;
		this.pokemons = pokemons;
	}
	public Pokedex(int id) {
		this.id = id;
		this.pokemons = new ArrayList<>();
	}
	public int getId() {
		return this.id;
	}
	public List<Pokemon> getPokemons() {
		return this.pokemons;
	}

	@Override
	public String toString() {
		return "Pokedex [id=" + id + ", pokemons=" + pokemons + "]";
	}
	
}
