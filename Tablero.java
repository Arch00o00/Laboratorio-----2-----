import java.util.*;

public class Tablero {
    private Ficha[][] fichas;
    private int filas;
    private int columnas;
    private String[] simbolos;
    
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.fichas = new Ficha[filas][columnas];
        this.simbolos = new String[]{"🐶", "🐱", "🐭", "🐹", "🐰", "🦊", "🐻", "🐼", "🐨", "🐯", "🦁", "🐮", "🐷", "🐸", "🐵", "🐔", "🐧", "🐦"};
        inicializarTablero();
    }
    
    private void inicializarTablero() {
        int totalCasillas = filas * columnas;
        int totalPares = totalCasillas / 2;
        
        List<String> simbolosTablero = new ArrayList<>();
        for (int i = 0; i < totalPares; i++) {
            simbolosTablero.add(simbolos[i]);
            simbolosTablero.add(simbolos[i]);
        }
        
        Collections.shuffle(simbolosTablero);
        
        int indice = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                fichas[i][j] = new Ficha(simbolosTablero.get(indice++));
            }
        }
    }
    
    public Ficha getFicha(int fila, int columna) {
        return fichas[fila][columna];
    }
    
    public int getFilas() {
        return filas;
    }
    
    public int getColumnas() {
        return columnas;
    }
    
    public boolean posicionValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }
    
    public boolean fichaDisponible(int fila, int columna) {
        return !fichas[fila][columna].isEmparejada() && !fichas[fila][columna].isRevelada();
    }
    
    public void revelarFicha(int fila, int columna) {
        fichas[fila][columna].revelar();
    }
    
    public void ocultarFicha(int fila, int columna) {
        fichas[fila][columna].ocultar();
    }
    
    public void emparejarFichas(int fila1, int columna1, int fila2, int columna2) {
        fichas[fila1][columna1].emparejar();
        fichas[fila2][columna2].emparejar();
    }
    
    public boolean sonIguales(int fila1, int columna1, int fila2, int columna2) {
        return fichas[fila1][columna1].getSimbolo().equals(fichas[fila2][columna2].getSimbolo());
    }
    
    public boolean juegoTerminado() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!fichas[i][j].isEmparejada()) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void mostrarTablero() {
        System.out.print("   ");
        for (int j = 0; j < columnas; j++) {
            System.out.printf("%3d", j);
        }
        System.out.println();
        
        for (int i = 0; i < filas; i++) {
            System.out.printf("%2d ", i);
            for (int j = 0; j < columnas; j++) {
                System.out.printf("%3s", fichas[i][j].toString());
            }
            System.out.println();
        }
    }
    
    public void reiniciarTablero() {
        inicializarTablero();
    }
}
