
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import cu.edu.cujae.ceis.tree.binary.BinaryTreeNode;
import cu.edu.cujae.ceis.tree.general.GeneralTree;
import cu.edu.cujae.ceis.tree.iterators.ITreeIterator;
import cu.edu.cujae.ceis.tree.iterators.general.InDepthIterator;

public class Aplicacion {
    private GeneralTree<Plataforma> appPlataforma;

    public Aplicacion(){
        this.appPlataforma = new GeneralTree<>();
    }

    //---METODOS AUXILIARES
    public BinaryTreeNode<Plataforma> encontrarNodo(String buscarNombre){
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

    //---INCISO B---
    public LinkedList<Cancion> cancionMasReproducida(){
        LinkedList<Cancion> masRep = new LinkedList<>();
        int max = -1;

        InDepthIterator<Plataforma> it = appPlataforma.inDepthIterator();
        while (it.hasNext()) {
            Object info = it.next();
            if(info instanceof Cancion){
                Cancion actual = (Cancion)info;
                if(actual.getCantReproducciones() > max){
                    max = actual.getCantReproducciones();
                    masRep.add(actual);//Puede haber mas de una cancion con la cant max de reproducciones
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
                    BinaryTreeNode<Plataforma> cancionNodo = new BinaryTreeNode<>(actual);
                    appPlataforma.insertNode(cancionNodo, artistaNodo);
                }
                Album albumActual = (Album)albumNodo.getInfo();
                LinkedList<BinaryTreeNode<Plataforma>> hijos = appPlataforma.getSons(albumNodo);
                int total = hijos.size();
                albumActual.setCantCanciones(total);
                exito = true;
            }
        }
        return exito;
    }

    //---INCISO E---
    public LinkedList obtenerListaReproduccion()
}
