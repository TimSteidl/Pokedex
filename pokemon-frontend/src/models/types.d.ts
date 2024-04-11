export type Pokedex = {
  id: number
  pokemons: Pokemon[]
}

export type Pokemon = {
  id: number
  name: string
  type1: (typeof Type)[keyof typeof Type]
  type2: (typeof Type)[keyof typeof Type]
  total: number
  hp: number
  attack: number
  defense: number
  specialAttack: number
  specialDefense: number
  speed: number
}

export const Type = {
  BUG: 'Bug',
  DARK: 'Dark',
  DRAGON: 'Dragon',
  ELECTRIC: 'Electric',
  FAIRY: 'Fairy',
  FIGHTING: 'Fighting',
  FIRE: 'Fire',
  FLYING: 'Flying',
  GHOST: 'Ghost',
  GRASS: 'Grass',
  GROUND: 'Ground',
  ICE: 'Ice',
  NORMAL: 'Normal',
  POISON: 'Poison',
  PSYCHIC: 'Psychic',
  ROCK: 'Rock',
  STEEL: 'Steel',
  WATER: 'Water',
  NONE: 'None',
} as const
