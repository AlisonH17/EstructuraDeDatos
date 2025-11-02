import java.util.ArrayList;

public class SelloDiscografico extends Plataforma{
    private String nombre;
    private String pais;
    private int anyoFundacion;
    private ArrayList<Artista>artistas;

 
    public SelloDiscografico(String nombre, int anyoCreacion, String nombre2, String pais, int anyoFundacion) {
        super(nombre, anyoCreacion);
        nombre = nombre2;
        this.pais = pais;
        this.anyoFundacion = anyoFundacion;
        this.artistas = new ArrayList<>();
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public int getAnyoFundacion() {
        return anyoFundacion;
    }
    public void setAnyoFundacion(int anyoFundacion) {
        this.anyoFundacion = anyoFundacion;
    }
    public ArrayList<Artista> getArtistas() {
        return artistas;
    }
    public void setArtistas(ArrayList<Artista> artistas) {
        this.artistas = artistas;
    }
    
    //Metodos
    public void agregarArtista(Artista a){
        artistas.add(a);
    }

    public void eliminarArtista(Artista a){
        artistas.remove(a);
    }
    public ArrayList<Artista> listaArtistas(){
        ArrayList<Artista>lista= new ArrayList<>();
        for(Artista a : artistas){
            lista.add(a);
        }
        return lista;
    }
}
