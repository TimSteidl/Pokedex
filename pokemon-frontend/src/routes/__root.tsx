import { ModeToggle } from '@/components/mode-toggle'
import { ThemeProvider } from '@/components/theme-provider'
import { Button } from '@/components/ui/button'
import { Toaster } from '@/components/ui/sonner'
import { createRootRoute, Link, Outlet } from '@tanstack/react-router'
import { TanStackRouterDevtools } from '@tanstack/router-devtools'
import { Home, Table } from 'lucide-react'

export const Route = createRootRoute({
  component: () => (
    <ThemeProvider defaultTheme="light">
      <div className="p-2 flex gap-2">
        <Button size={'icon'} variant={'outline'}>
          <Link to="/">
            <Home />
          </Link>
        </Button>
        <Button size={'icon'} variant={'outline'}>
          <Link to="/pokedex">
            <Table />
          </Link>
        </Button>
        <ModeToggle />
      </div>
      <hr />
      <div className="justify-center flex">
        <Outlet />
      </div>
      <TanStackRouterDevtools />
      <Toaster />
    </ThemeProvider>
  ),
})
