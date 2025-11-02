import java.util.ArrayList;

public class Album {
   private String titulo;
   private String anyoLanzamiento;
   private String genero;
   private int cantCanciones;
   private ArrayList<Cancion> canciones;
   //Constructor
   public Album(String titulo, String anyoLanzamiento, String genero, int cantCanciones) {
     this.titulo = titulo;
     this.anyoLanzamiento = anyoLanzamiento;
     this.genero = genero;
     this.cantCanciones = cantCanciones;
     this.canciones = new ArrayList<>();
   }
      //Getters y Setters
   public String getTitulo() {
    return titulo;
   }
   public void setTitulo(String titulo) {
    this.titulo = titulo;
   }
   public String getAnyoLanzamiento() {
    return anyoLanzamiento;
   }
   public void setAnyoLanzamiento(String anyoLanzamiento) {
    this.anyoLanzamiento = anyoLanzamiento;
   }
   public String getGenero() {
    return genero;
   }
   public void setGenero(String genero) {
    this.genero = genero;
   }
   public int getCantCanciones() {
    return cantCanciones;
   }
   public void setCantCanciones(int cantCanciones) {
    this.cantCanciones = cantCanciones;
   }
   public ArrayList<Cancion> getCanciones() {
    return canciones;
   }
   public void setCanciones(ArrayList<Cancion> canciones) {
    this.canciones = canciones;
   }
       //Metodos
    public void agregarCancion(Cancion c){
        canciones.add(c);
    }

    public void eliminarCancion(Cancion c){
        canciones.remove(c);
    }
}
