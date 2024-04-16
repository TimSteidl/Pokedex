package de.bredex.pokemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import de.bredex.enums.Type;
import de.bredex.pokedex.Pokedex;
import de.bredex.pokedex.PokedexRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/pokemons")
@CrossOrigin("*")
public class PokemonController {


    private PokemonRepository pokemonRepository;

    private PokedexRepository pokedexRepository;

    public PokemonController(PokemonRepository pokemonRepository, PokedexRepository pokedexRepository) {
        this.pokemonRepository = pokemonRepository;
        this.pokedexRepository = pokedexRepository;
    }

    @GetMapping("/filtered")
    public List<Pokemon> getFilteredPokemons(@RequestParam int id, @RequestParam String name, @RequestParam String type1, 
                                            @RequestParam String type2, @RequestParam String attribute, @RequestParam String operation, 
                                            @RequestParam("value") int value) {
        List<Pokemon> pokemons = this.pokemonRepository.getFilteredPokemon(id, name, type1, type2);
        System.err.println(pokemons.size());
        switch (attribute) {
            case "total" -> {
                System.out.println(attribute + " " + operation + " " + value);
                switch (operation) {
                    case ">" -> pokemons = pokemons.stream().filter(p -> p.getTotal() < value).toList();
                    case "=" -> pokemons = pokemons.stream().filter(p -> p.getTotal() == value).toList();
                    default -> pokemons =  pokemons.stream().filter(p -> p.getTotal() > value).toList();
                }
            }
            case "speed" -> {
                switch (operation) {
                    case ">" -> pokemons = pokemons.stream().filter(p -> p.getSpeed() < value).toList();
                    case "=" ->pokemons =  pokemons.stream().filter(p -> p.getSpeed() == value).toList();
                    default -> pokemons = pokemons.stream().filter(p -> p.getSpeed() > value).toList();
                }
            }
            case "hp" -> {
                switch (operation) {
                    case ">" -> pokemons = pokemons.stream().filter(p -> p.getHp() < value).toList();
                    case "=" -> pokemons = pokemons.stream().filter(p -> p.getHp() == value).toList();
                    default -> pokemons = pokemons.stream().filter(p -> p.getHp() > value).toList();
                }
            }
            case "attack" -> {
                switch (operation) {
                    case ">" -> pokemons = pokemons.stream().filter(p -> p.getAttack() < value).toList();
                    case "=" -> pokemons = pokemons.stream().filter(p -> p.getAttack() == value).toList();
                    default -> pokemons = pokemons.stream().filter(p -> p.getAttack() > value).toList();
                }
            }
            case "specialAttack" -> {
                switch (operation) {
                    case ">" -> pokemons = pokemons.stream().filter(p -> p.getSpecialAttack() < value).toList();
                    case "=" -> pokemons = pokemons.stream().filter(p -> p.getSpecialAttack() == value).toList();
                    default -> pokemons = pokemons.stream().filter(p -> p.getSpecialAttack() > value).toList();
                }
            }
            case "specialDefense" -> {
                switch (operation) {
                    case ">" -> pokemons = pokemons.stream().filter(p -> p.getTotal() > value).toList();
                    case "=" -> pokemons = pokemons.stream().filter(p -> p.getTotal() == value).toList();
                    default -> pokemons = pokemons.stream().filter(p -> p.getTotal() < value).toList();
                }
            }
            default -> {
                switch (operation) {
                    case ">" -> pokemons = pokemons.stream().filter(p -> p.getDefense() < value).toList();
                    case "=" -> pokemons = pokemons.stream().filter(p -> p.getDefense() == value).toList();
                    default -> pokemons = pokemons.stream().filter(p-> p.getDefense() > value).toList();
                }
            }
        }
        System.err.println(pokemons.size());
        return pokemons;
    }
    

    @PostMapping("/upload")
    public int uploadFile(@RequestBody MultipartFile file) {
        Pokedex initialPokedex = this.pokedexRepository.save(new Pokedex());
        int pokedexId = initialPokedex.getId();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // read first header line
            String line = reader.readLine();
            List<Pokemon> readPokemons = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                var pokemonCsv = line.split(",");

                var id = Integer.parseInt(pokemonCsv[0]);
                var name = pokemonCsv[1].replace("?", "");
                var type1 = pokemonCsv[2] != null && !pokemonCsv[2].isEmpty()
                        ? Type.valueOf(pokemonCsv[2].toUpperCase())
                        : null;
                var type2 = pokemonCsv[3] != null && !pokemonCsv[3].isEmpty()
                        ? Type.valueOf(pokemonCsv[3].toUpperCase())
                        : null;
                var total = Integer.parseInt(pokemonCsv[4]);
                var hp = Integer.parseInt(pokemonCsv[5]);
                var attack = Integer.parseInt(pokemonCsv[6]);
                var defense = Integer.parseInt(pokemonCsv[7]);
                var spAttack = Integer.parseInt(pokemonCsv[8]);
                var spDefense = Integer.parseInt(pokemonCsv[9]);
                var speed = Integer.parseInt(pokemonCsv[10]);
                var pokedex = this.pokedexRepository.findById(pokedexId);
                if(pokedex.isEmpty()) {
                    System.err.println("Pokedex not found");
                } else {
                    Pokemon pokemon = new Pokemon(id, name, type1, type2, total, hp, attack, defense, spAttack,
                        spDefense, speed, pokedex.get());
                    this.pokemonRepository.save(pokemon);
                    readPokemons.add(pokemon);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokedexId;
    }
}
