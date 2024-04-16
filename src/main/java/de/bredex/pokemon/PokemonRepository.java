package de.bredex.pokemon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{
	@Query(value="SELECT * FROM pokemon WHERE pokedex_id = :id AND INSTR(name, :name) > 0 AND INSTR(type1, :type1) > 0 AND INSTR(type2, :type2) > 0", nativeQuery=true)
    List<Pokemon> getFilteredPokemon(
        @Param(value = "id") int pokedexId,
        @Param(value = "name") String name,
        @Param(value = "type1") String type1,
        @Param(value = "type2") String type2
        );
}
