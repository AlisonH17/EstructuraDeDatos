public class ListaRep {
    private String nombreArt;
    private int cantCanciones;
    
    public ListaRep(String nombreArt, int cantCanciones) {
        this.nombreArt = nombreArt;
        this.cantCanciones = cantCanciones;
    }
    
    public String getNombreArt() {
        return nombreArt;
    }
    public void setNombreArt(String nombreArt) {
        this.nombreArt = nombreArt;
    }
    public int getCantCanciones() {
        return cantCanciones;
    }
    public void setCantCanciones(int cantCanciones) {
        this.cantCanciones = cantCanciones;
    }
    
}
