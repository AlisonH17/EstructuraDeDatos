
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

public class PlataformaMusical {
    private String nombre;
    private int anyoCreacion;
    private GeneralTree<Plataforma> appPlataforma;

    public PlataformaMusical(String nombre,int anyo){
        this.nombre = nombre;
        this.anyoCreacion = anyo;
        this.appPlataforma = new GeneralTree<>();
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

    private Artista buscArtista(String nombreArt){
        Artista art= null;
        boolean encontrado = false;
        ITreeIterator<Plataforma> it = appPlataforma.inDepthIterator();
        while(it.hasNext() && ! encontrado){
            BinaryTreeNode<Plataforma> actual = it.nextNode();
            Object info = actual.getInfo();
            if(info instanceof SelloDiscografico){
                ArrayList<Artista>lista= ((SelloDiscografico)info).listaArtistas();
                Iterator<Artista>arts= lista.iterator();
                while(arts.hasNext()&& !encontrado){
                    if(arts.next().getNombre().equals(nombreArt)){
                        encontrado= true;
                        art= arts.next();
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
    public Queue<Cancion> obtenerListaReproduccion(LinkedList<ListaRep>artistasPedidos){
        Queue<Cancion> playlist = new ArrayDeque<>();
        Iterator<ListaRep> it = artistasPedidos.iterator();
        while (it.hasNext()) {
            String nombre = it.next().getNombreArt();
            int cant = it.next().getCantCanciones();
            int añadidas = 0;
            Artista artEncontrado= buscArtista(nombre);
            if( artEncontrado != null){
                Iterator<Album> albIt=artEncontrado.getAlbumes().iterator();
                while(albIt.hasNext() && añadidas < cant){
                    Album album = albIt.next();
                    Iterator<Cancion> cancIt = album.getCanciones().iterator();

                    while(cancIt.hasNext() && añadidas < cant){
                        Cancion c = cancIt.next();

                        playlist.offer(c);
                        añadidas++;
                    }
                }
            }
        }
        return playlist;
    }
}
