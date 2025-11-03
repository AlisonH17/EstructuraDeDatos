
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;
import cu.edu.cujae.ceis.tree.iterators.general.InDepthIterator;

public class PlataformaMusical implements Plataforma{
    private static PlataformaMusical instance ; //Singleton...DPOO me persigue
    private String nombre;
    private int anyoCreacion;
    private GeneralTree<Plataforma> appPlataforma;

    private PlataformaMusical(String nombre,int anyo){
        this.nombre = nombre;
        this.anyoCreacion = anyo;
        this.appPlataforma = new GeneralTree<>();
        BinaryTreeNode<Plataforma> raiz = new BinaryTreeNode<>(this);
        appPlataforma.setRoot(raiz);
    }

    //Unica Instancia (Singleton)
    public static PlataformaMusical getInstance(String nombre, int anyoCreacion) {
        if (instance == null) {
            instance = new PlataformaMusical(nombre, anyoCreacion);
        }
        return instance;
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

    //---METODOS AUXILIARES
    private BinaryTreeNode<Plataforma> encontrarNodo(String buscarNombre){
        BinaryTreeNode<Plataforma> nodo = null;
        boolean encontrado = false;

        ITreeIterator<Plataforma> it = appPlataforma.inDepthIterator();
        
        while (it.hasNext() && !encontrado) {
            BinaryTreeNode<Plataforma>actual = it.nextNode();
            Object info = actual.getInfo();
            if(info instanceof Artista && ((Artista)info).getNombre().equals(buscarNombre)){
            nodo = actual;
            encontrado = true;
            }else if(info instanceof SelloDiscografico && ((SelloDiscografico)info).getNombre().equals(buscarNombre)){
            nodo = actual;
            encontrado = true;
            }else if(info instanceof Album && ((Album)info).getTitulo().equals(buscarNombre)){
            nodo = actual;
            encontrado = true;
            }
        }
        return nodo;
    }

    private Artista buscArtista(String nombreArt) {
        Artista art = null;
        boolean encontrado = false;
        ITreeIterator<Plataforma> it = appPlataforma.inDepthIterator();
        while (it.hasNext() && !encontrado) {
            BinaryTreeNode<Plataforma> actual = it.nextNode();
            Object info = actual.getInfo();
            if (info instanceof SelloDiscografico) {
                ArrayList<Artista> lista = ((SelloDiscografico) info).listaArtistas();
                Iterator<Artista> arts = lista.iterator();
                while (arts.hasNext() && !encontrado) {
                    Artista temp = arts.next();  
                    if (temp.getNombre().equals(nombreArt)) {
                        encontrado = true;
                        art = temp;
                    }
                }
            }
        }
        return art;
    }

    //---INCISO B---
    public LinkedList<Cancion> cancionMasReproducida(){
        LinkedList<Cancion> masRep = new LinkedList<>();
        int max = -1;
        InDepthIterator<Plataforma> it = appPlataforma.inDepthIterator();
        while (it.hasNext()) {
            Plataforma info = it.next();
            if (info instanceof Cancion) {
                Cancion actual = (Cancion) info;
                if (actual.getCantReproducciones() > max) {
                    max = actual.getCantReproducciones();
                    masRep.clear();
                    masRep.add(actual);
                } else if (actual.getCantReproducciones() == max) {
                    masRep.add(actual);
                }
            }
        }
        return masRep;
    }

    //---INCISO C---
    public boolean transferirArtistaDeSello(String nombreArt,String nuevoSello){
        boolean transferido = false;
        BinaryTreeNode<Plataforma> artistaNodo = encontrarNodo(nombreArt);
        BinaryTreeNode<Plataforma> selloNodo = encontrarNodo(nuevoSello);
        if(artistaNodo != null && selloNodo != null){
            BinaryTreeNode<Plataforma> anteriorSello = appPlataforma.getFather(artistaNodo);
            if(anteriorSello != null && !anteriorSello.equals(selloNodo)){
                appPlataforma.deleteNode(artistaNodo);
                transferido = appPlataforma.insertNode(artistaNodo, selloNodo);
            }
        }
        return transferido;
    }

    //---INCISO D---
    public boolean agregarCancionesAlbum(String nombreArt,ArrayList<Cancion> cancionesNuevas,String tituloAlbum){
        boolean exito = false;
        BinaryTreeNode<Plataforma> albumNodo = encontrarNodo(tituloAlbum);

        if(albumNodo !=null){
            BinaryTreeNode<Plataforma> artistaNodo = appPlataforma.getFather(albumNodo);
            if(artistaNodo !=null ){
                Iterator<Cancion> iterator = cancionesNuevas.iterator();
                while(iterator.hasNext()){
                    Cancion actual = iterator.next();
                    BinaryTreeNode<Plataforma> cancionNodo = new BinaryTreeNode<>((Plataforma) actual);
                    appPlataforma.insertNode(cancionNodo, albumNodo);
                }
                Album albumActual = (Album)albumNodo.getInfo();
                List<BinaryTreeNode<Plataforma>> hijos = appPlataforma.getSons(albumNodo);
                int total = hijos.size();
                albumActual.setCantCanciones(total);
                exito = true;
            }
        }
        return exito;
    }

    //---INCISO E---
    public Queue<Cancion> obtenerListaReproduccion(LinkedList<ListaRep> artistasPedidos) {
        Queue<Cancion> playlist = new ArrayDeque<>();
        Iterator<ListaRep> it = artistasPedidos.iterator();
        while (it.hasNext()) {
            ListaRep rep = it.next(); 
            String nombre = rep.getNombreArt();
            int cant = rep.getCantCanciones();
            int añadidas = 0;
            Artista artEncontrado = buscArtista(nombre);
            if (artEncontrado != null) {
                Iterator<Album> albIt = artEncontrado.getAlbumes().iterator();
                while (albIt.hasNext() && añadidas < cant) {
                    Album album = albIt.next();
                    Iterator<Cancion> cancIt = album.getCanciones().iterator();
                    while (cancIt.hasNext() && añadidas < cant) {
                        Cancion c = cancIt.next();
                        playlist.offer(c);
                        añadidas++;
                    }
                }
            }
        }
        return playlist;
    }
    // Método para imprimir el árbol con jerarquía
    public void imprimirArbol() {
        System.out.println("Estructura del Árbol de la Plataforma:");
        imprimirNodo((BinaryTreeNode<Plataforma>) appPlataforma.getRoot(), 0);
    }
    // Método auxiliar recursivo para imprimir nodos con indentación
    private void imprimirNodo(BinaryTreeNode<Plataforma> nodo, int nivel) {
        if (nodo == null) return;
        for (int i = 0; i < nivel; i++) System.out.print("  ");
        Plataforma info = nodo.getInfo();
        if(info.getAnyoCreacion() != -1){
        System.out.println("- " + info.getClass().getSimpleName() + ": " + info.getNombre() + " (Año: " + info.getAnyoCreacion() + ")");
        }else{
            System.out.println("- "+ info.getClass().getSimpleName() + ": "+info.getNombre());
        }
        for (BinaryTreeNode<Plataforma> hijo : appPlataforma.getSons(nodo)) {
            imprimirNodo(hijo, nivel + 1);
        }
    
    }

    // Métodos adicionales para gestionar el árbol(Para prueba de Main)
    public void agregarSello(SelloDiscografico sello) {
        BinaryTreeNode<Plataforma> selloNodo = new BinaryTreeNode<Plataforma>(sello);
       appPlataforma.insertNode(selloNodo, (BinaryTreeNode<Plataforma>) appPlataforma.getRoot());
    }
    // Método para agregar un artista a un sello (necesario para el árbol)
    public void agregarArtistaASello(String nombreSello, Artista artista) {
        BinaryTreeNode<Plataforma> selloNodo = encontrarNodo(nombreSello);
        if (selloNodo != null) {
            BinaryTreeNode<Plataforma> artistaNodo = new BinaryTreeNode<Plataforma>(artista);
            appPlataforma.insertNode(artistaNodo, selloNodo);
            // También agregar a la lista del sello
            ((SelloDiscografico) selloNodo.getInfo()).agregarArtista(artista);
        }
    }
    // Método para agregar un álbum a un artista
    public void agregarAlbumAArtista(String nombreArtista, Album album) {
        BinaryTreeNode<Plataforma> artistaNodo = encontrarNodo(nombreArtista);
        if (artistaNodo != null) {
            BinaryTreeNode<Plataforma> albumNodo = new BinaryTreeNode<Plataforma>(album);
            appPlataforma.insertNode(albumNodo, artistaNodo);
            // También agregar a la lista del artista
            ((Artista) artistaNodo.getInfo()).agregarAlbum(album);
        }
    }
}
