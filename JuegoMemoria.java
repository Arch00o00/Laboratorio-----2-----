public class JuegoMemoria {
    private Tablero tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorActual;
    public JuegoMemoria(int filas, int columnas, String nombreJugador1, String nombreJugador2) {
        this.tablero = new Tablero(filas, columnas);
        this.jugador1 = new Jugador(nombreJugador1);
        this.jugador2 = new Jugador(nombreJugador2);
        this.jugadorActual = jugador1;
    }
    
    public Tablero getTablero() {
        return tablero;
    }
    
    public Jugador getJugador1() {
        return jugador1;
    }
    
    public Jugador getJugador2() {
        return jugador2;
    }
    
    public Jugador getJugadorActual() {
        return jugadorActual;
    }
    
    public void cambiarTurno() {
        jugadorActual = (jugadorActual == jugador1) ? jugador2 : jugador1;
    }
    
    public boolean realizarJugada(int fila1, int columna1, int fila2, int columna2) {
        if (!validarJugada(fila1, columna1, fila2, columna2)) {
            return false;
        }
        
        tablero.revelarFicha(fila1, columna1);
        tablero.revelarFicha(fila2, columna2);
        
        if (tablero.sonIguales(fila1, columna1, fila2, columna2)) {
            tablero.emparejarFichas(fila1, columna1, fila2, columna2);
            jugadorActual.incrementarPuntos();
            return true;
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            tablero.ocultarFicha(fila1, columna1);
            tablero.ocultarFicha(fila2, columna2);
            cambiarTurno();
            return false;
        }
    }
    
    private boolean validarJugada(int fila1, int columna1, int fila2, int columna2) {
        if (!tablero.posicionValida(fila1, columna1) || !tablero.posicionValida(fila2, columna2)) {
            return false;
        }
        
        if (fila1 == fila2 && columna1 == columna2) {
            return false;
        }
        
        if (!tablero.fichaDisponible(fila1, columna1) || !tablero.fichaDisponible(fila2, columna2)) {
            return false;
        }
        
        return true;
    }
    
    public boolean juegoTerminado() {
        return tablero.juegoTerminado();
    }
    
    public Jugador obtenerGanador() {
        if (jugador1.getPuntos() > jugador2.getPuntos()) {
            return jugador1;
        } else if (jugador2.getPuntos() > jugador1.getPuntos()) {
            return jugador2;
        }
        return null;
    }
    
    public void mostrarResultados() {
        System.out.println("\n=== RESULTADOS FINALES ===");
        System.out.println(jugador1.toString());
        System.out.println(jugador2.toString());
        
        Jugador ganador = obtenerGanador();
        if (ganador != null) {
            System.out.println("¡Ganador: " + ganador.getNombre() + "!");
        } else {
            System.out.println("¡Empate!");
        }
    }
    
    public void reiniciarJuego() {
        tablero.reiniciarTablero();
        jugador1.reiniciarPuntos();
        jugador2.reiniciarPuntos();
        jugadorActual = jugador1;
    }
    
    public void mostrarEstadoJuego() {
        System.out.println("\n" + jugador1.toString());
        System.out.println(jugador2.toString());
        System.out.println("Turno de: " + jugadorActual.getNombre());
        System.out.println();
        tablero.mostrarTablero();
    }
}
