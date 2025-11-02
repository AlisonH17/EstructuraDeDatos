import java.util.ArrayList;

public class Plataforma {
   private String nombre;
   private int anyoCreacion;
   private ArrayList<SelloDiscografico> sellosDisc;

   public ArrayList<SelloDiscografico> getSellosDisc() {
    return sellosDisc;
   }

   public void setSellosDisc(ArrayList<SelloDiscografico> sellosDisc) {
    this.sellosDisc = sellosDisc;
   }

   public Plataforma(String nombre, int anyoCreacion) {
    this.nombre = nombre;
    this.anyoCreacion = anyoCreacion;
    this.sellosDisc = new ArrayList<>();
   }

   public String getNombre() {
    return nombre;
   }

   public void setNombre(String nombre) {
    this.nombre = nombre;
   }

   public int getAnyoCreacion() {
    return anyoCreacion;
   }

   public void setAnyoCreacion(int anyoCreacion) {
    this.anyoCreacion = anyoCreacion;
   }
    
   public void agregarSelloDisc(SelloDiscografico s){
    sellosDisc.add(s);
   }

   public void eliminarSelloDisc(SelloDiscografico s){
    sellosDisc.remove(s);
   }
}
