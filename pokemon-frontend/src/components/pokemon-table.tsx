import { Pokemon } from '@/models/types'
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from './ui/table'

interface PokemonTableProps {
  pokemons: Pokemon[]
}

export function PokemonTable(props: PokemonTableProps) {
  return (
    <Table>
      <TableHeader>
        <TableRow>
          <TableHead>Id</TableHead>
          <TableHead>Name</TableHead>
          <TableHead>Type</TableHead>
          <TableHead>Type 2</TableHead>
          <TableHead>Speed</TableHead>
          <TableHead>HP</TableHead>
          <TableHead>Attack</TableHead>
          <TableHead>Defense</TableHead>
          <TableHead>Sp. Atk</TableHead>
          <TableHead>Sp. Def</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {props?.pokemons.map((p) => (
          <TableRow key={p.id}>
            <TableCell>{p.id}</TableCell>
            <TableCell>{p.name}</TableCell>
            <TableCell>{p.type1}</TableCell>
            <TableCell>{p.type2 !== 'None' ? p.type2 : 'None'}</TableCell>
            <TableCell>{p.speed}</TableCell>
            <TableCell>{p.hp}</TableCell>
            <TableCell>{p.attack}</TableCell>
            <TableCell>{p.defense}</TableCell>
            <TableCell>{p.specialAttack}</TableCell>
            <TableCell>{p.specialDefense}</TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  )
}
