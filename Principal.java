import java.util.*;

public class Principal {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        mostrarBienvenida();
        
        boolean continuarJugando = true;
        while (continuarJugando) {
            JuegoMemoria juego = configurarJuego();
            jugarPartida(juego);
            continuarJugando = preguntarNuevaPartida();
        }
        
        System.out.println("¡Gracias por jugar!");
        scanner.close();
    }
    
    private static void mostrarBienvenida() {
        System.out.println("=================================");
        System.out.println("    JUEGO DE MEMORIA - PAREJAS   ");
        System.out.println("=================================");
        System.out.println("¡Bienvenidos al juego de memoria!");
        /**System.out.println("Dos jugadores compiten por turnos");*/
        /**System.out.println("para encontrar pares de fichas."); */
        System.out.println();
    }
    
    private static JuegoMemoria configurarJuego() {
        System.out.println("=== CONFIGURACIÓN DEL JUEGO ===");
        
        String nombreJugador1 = solicitarNombreJugador(1);
        String nombreJugador2 = solicitarNombreJugador(2);
        
        int[] dimensiones = seleccionarTamanoTablero();
        
        return new JuegoMemoria(dimensiones[0], dimensiones[1], nombreJugador1, nombreJugador2);
    }
    
    private static String solicitarNombreJugador(int numeroJugador) {
        System.out.print("Ingrese el nombre del Jugador " + numeroJugador + ": ");
        return scanner.nextLine().trim();
    }
    
    private static int[] seleccionarTamanoTablero() {
        System.out.println("\nSeleccione el tamaño del tablero:");
        System.out.println("1. 4x4 (8 pares)");
        System.out.println("2. 6x6 (18 pares)");
        System.out.println("3. 4x6 (12 pares)");
        
        int opcion = 0;
        boolean opcionValida = false;
        
        while (!opcionValida) {
            try {
                System.out.print("Opción (1-3): ");
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion >= 1 && opcion <= 3) {
                    opcionValida = true;
                } else {
                    System.out.println("Por favor, seleccione una opción válida (1-3).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
        
        switch (opcion) {
            case 1: return new int[]{4, 4};
            case 2: return new int[]{6, 6};
            case 3: return new int[]{4, 6};
            default: return new int[]{4, 4};
        }
        
    }
    
    private static void jugarPartida(JuegoMemoria juego) {
        System.out.println("\n¡Comienza el juego!");
        
        while (!juego.juegoTerminado()) {
            juego.mostrarEstadoJuego();
            realizarTurno(juego);
        }
        
        juego.mostrarResultados();
    }
    
    private static void realizarTurno(JuegoMemoria juego) {
        boolean jugadaValida = false;
        
        while (!jugadaValida) {
            try {
                System.out.println("\n" + juego.getJugadorActual().getNombre() + ", es tu turno.");
                System.out.println("Selecciona dos casillas (fila columna):");
                
                System.out.print("Primera casilla (fila columna): ");
                String[] primera = scanner.nextLine().split(" ");
                int fila1 = Integer.parseInt(primera[0]);
                int columna1 = Integer.parseInt(primera[1]);
                
                System.out.print("Segunda casilla (fila columna): ");
                String[] segunda = scanner.nextLine().split(" ");
                int fila2 = Integer.parseInt(segunda[0]);
                int columna2 = Integer.parseInt(segunda[1]);
                
                if (validarEntrada(juego, fila1, columna1, fila2, columna2)) {
                    boolean acierto = juego.realizarJugada(fila1, columna1, fila2, columna2);
                    
                    System.out.println("\nRevelando fichas...");
                    juego.getTablero().mostrarTablero();
                    
                    if (acierto) {
                        System.out.println("¡Excelente! " + juego.getJugadorActual().getNombre() + " encontró un par.");
                        System.out.println("Conservas el turno.");
                    } else {
                        System.out.println("Las fichas no coinciden. Turno del siguiente jugador.");
                    }
                    
                    jugadaValida = true;
                } else {
                    System.out.println("Jugada inválida. Intenta de nuevo.");
                }
                
            } catch (Exception e) {
                System.out.println("Entrada inválida. Use el formato: fila columna");
            }
        }
    }
    
    private static boolean validarEntrada(JuegoMemoria juego, int fila1, int columna1, int fila2, int columna2) {
        Tablero tablero = juego.getTablero();
        
        if (!tablero.posicionValida(fila1, columna1)) {
            System.out.println("La primera posición está fuera del tablero.");
            return false;
        }
        
        if (!tablero.posicionValida(fila2, columna2)) {
            System.out.println("La segunda posición está fuera del tablero.");
            return false;
        }
        
        if (fila1 == fila2 && columna1 == columna2) {
            System.out.println("No puedes seleccionar la misma casilla dos veces.");
            return false;
        }
        
        if (!tablero.fichaDisponible(fila1, columna1)) {
            System.out.println("La primera ficha ya está emparejada o revelada.");
            return false;
        }
        
        if (!tablero.fichaDisponible(fila2, columna2)) {
            System.out.println("La segunda ficha ya está emparejada o revelada.");
            return false;
        }
        
        return true;
    }
    
    private static boolean preguntarNuevaPartida() {
        System.out.print("\n¿Desean jugar otra partida? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí");
    }
}
