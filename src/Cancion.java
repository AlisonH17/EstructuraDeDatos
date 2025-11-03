
public class Cancion implements Plataforma {
    private String titulo;
    private double duracion;
    private int numPistas;
    private int cantReproducciones;
    //Constructor
    public Cancion(String titulo, double duracion, int numPistas,int cantReproducciones) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.numPistas = numPistas;
        this.cantReproducciones = cantReproducciones;
    }
    //Getters y Setters
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public double getDuracion() {
        return duracion;
    }
    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
    public int getNumPistas() {
        return numPistas;
    }
    public void setNumPistas(int numPistas) {
        this.numPistas = numPistas;
    }
    public int getCantReproducciones() {
        return cantReproducciones;
    }
    public void setCantReproducciones(int cantReproducciones) {
        this.cantReproducciones = cantReproducciones;
    }
    @Override
    public String getNombre() {
        return titulo;
    }
    @Override
    public int getAnyoCreacion() {
        return -1;  // No aplica
    }


}