import axios from 'axios'
import { BACKEND_BASE_URL } from './ENV'
import { toast } from 'sonner'

export async function postPokemon(file: File): Promise<number> {
  const form = new FormData()
  form.append('file', file)
  return await axios
    .post(BACKEND_BASE_URL + 'pokemons/upload', form, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    })
    .then((r) => r.data as number)
    .catch((e) => {
      toast('Error creating pokemon:' + e)
      return 0
    })
}
