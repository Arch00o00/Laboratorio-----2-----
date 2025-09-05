public class Ficha {
    private String simbolo;
    private boolean revelada;
    private boolean emparejada;
    
    public Ficha(String simbolo) {
        this.simbolo = simbolo;
        this.revelada = false;
        this.emparejada = false;
    }
    
    public String getSimbolo() {
        return simbolo;
    }
    
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }
    
    public boolean isRevelada() {
        return revelada;
    }
    
    public void setRevelada(boolean revelada) {
        this.revelada = revelada;
    }
    
    public boolean isEmparejada() {
        return emparejada;
    }
    
    public void setEmparejada(boolean emparejada) {
        this.emparejada = emparejada;
    }
    
    public void revelar() {
        this.revelada = true;
    }
    
    public void ocultar() {
        this.revelada = false;
    }
    
    public void emparejar() {
        this.emparejada = true;
        this.revelada = true;
    }
    
    @Override
    public String toString() {
        if (emparejada || revelada) {
            return simbolo;
        }
        return "?";
    }
}
