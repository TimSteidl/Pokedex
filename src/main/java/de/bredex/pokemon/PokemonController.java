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
