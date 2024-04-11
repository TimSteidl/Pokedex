package de.bredex.pokedex;

import java.util.List;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.bredex.pokemon.Pokemon;

@RequestMapping("/pokedex")
@RestController
@CrossOrigin("*")
public class PokedexController {

	
	private PokedexRepository pokedexRepo;

	public PokedexController(PokedexRepository pokedexRepository){
		this.pokedexRepo = pokedexRepository;
	}
	
	@GetMapping
	public Pokedex getPokedex(@RequestParam("id") int id) {
		return this.pokedexRepo.findById(id).orElse(null);
	}
	
	@PostMapping("/addOne/{id}")
	public void addPokemonToPokedex(@PathVariable int id, @RequestBody Pokemon pokemonToAdd) {
		Pokedex pokedex = this.pokedexRepo.findById(id).get();
		pokedex.getPokemons().add(pokemonToAdd);
		this.pokedexRepo.save(pokedex);
	}
	
	@PostMapping("/addMultiple/{id}")
	public void addPokemonsToPokedex(@PathVariable int id, @RequestBody List<Pokemon> pokemonsToAdd) {
		Pokedex pokedex = this.pokedexRepo.findById(id).get();
		pokedex.getPokemons().addAll(pokemonsToAdd);
		this.pokedexRepo.save(pokedex);
	}
}
