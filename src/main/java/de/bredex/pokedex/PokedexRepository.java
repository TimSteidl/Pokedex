package de.bredex.pokedex;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PokedexRepository extends JpaRepository<Pokedex, Integer> {
}
