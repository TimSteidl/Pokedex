import { Pokedex } from '@/models/types'
import axios from 'axios'
import { BACKEND_BASE_URL } from './ENV'
import { toast } from 'sonner'

export async function getPokedex(id: number): Promise<Pokedex> {
  return axios
    .get(BACKEND_BASE_URL + `pokedex?id=` + id)
    .then((res) => {
      return res.data as Pokedex
    })
    .catch((err) => {
      toast(err)
      return {} as Pokedex
    })
}

export async function postPokedex(pokemonToAdd: Pokedex, id: number): Promise<void> {
  await axios.post(BACKEND_BASE_URL + `pokedex/${id}`, pokemonToAdd)
}

export async function addPokemonToPokedex(pokemonsToAdd: Pokedex[], id: number): Promise<void> {
  await axios.post(BACKEND_BASE_URL + `pokedex/${id}`, pokemonsToAdd)
}
