import { getPokedex } from '@/api/pokedex'
import { postPokemon } from '@/api/pokemon'
import { PokemonTable } from '@/components/pokemon-table'
import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Pokemon } from '@/models/types'
import { createLazyFileRoute } from '@tanstack/react-router'
import { RefreshCw, Search, Trash, Upload } from 'lucide-react'
import { useEffect, useState } from 'react'
import { toast } from 'sonner'

export const Route = createLazyFileRoute('/pokedex')({
  component: Pokedex,
})

function Pokedex() {
  const [pokedexId, setPokedexId] = useState(0)
  const [pokemon, setPokemon] = useState<Pokemon[] | undefined>()
  const [file, setFile] = useState<File>()

  function getPokemon() {
    getPokedex(pokedexId).then((data) => {
      setPokemon(data.pokemons)
    })
  }

  function uploadFile() {
    console.log(file)
    if (file !== null && file !== undefined) {
      postPokemon(file).then((r) => {
        setPokedexId(r)
        toast('Created Pokedex with Id: ' + r)
      })
    }
  }

  useEffect(() => {
    if (pokedexId !== 0) {
      getPokemon()
    }
  }, [])

  return (
    <div className="flex-col w-3/4 border-x border-b rounded p-2">
      <h1>Pokedex</h1>
      <div className="flex-row flex">
        <Input
          type="number"
          placeholder="0"
          value={pokedexId}
          onChange={(e) => setPokedexId(parseInt(e.target.value))}
        />
        <div className="pl-2">
          <Button onClick={() => getPokemon()} className="w-24">
            <Search />
          </Button>
        </div>
        <div className="pl-2">
          <Button
            className="w-24"
            onClick={() => {
              setPokedexId(0)
              setPokemon(undefined)
            }}
          >
            <RefreshCw />
          </Button>
        </div>
      </div>
      {pokemon !== undefined ? (
        <PokemonTable pokemons={pokemon} />
      ) : (
        <>
          <h1>Upload File</h1>
          <div className="flex flex-row">
            <Input
              type="file"
              onChange={(e) => {
                const file = e.target.files[0]
                console.log(file + 'File')
                setFile(file)
              }}
            />
            <div className="pl-2">
              <Button className="w-24" onClick={() => uploadFile()}>
                <Upload />
              </Button>
            </div>
          </div>
        </>
      )}
    </div>
  )
}
