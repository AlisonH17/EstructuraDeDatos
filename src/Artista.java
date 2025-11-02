import java.util.ArrayList;

public class Artista {
    private String nombre;
    private String genero;
    private String nacionalidad;
    private ArrayList<Album>albumes;
    
    public Artista(String nombre, String genero, String nacionalidad) {
        this.nombre = nombre;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.albumes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public ArrayList<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(ArrayList<Album> albumes) {
        this.albumes = albumes;
    }
        //Metodos
    public void agregarAlbum(Album a){
        albumes.add(a);
    }
    public void eliminarAlbum(Album a){
        albumes.remove(a);
    }
}
