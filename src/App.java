import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class App {
    public static void main(String[] args) {
        // Obtener la instancia Singleton de la plataforma
        PlataformaMusical plataforma = PlataformaMusical.getInstance("MiMusica", 2020);
        System.out.println("Plataforma creada: " + plataforma.getNombre() + " (" + plataforma.getAnyoCreacion() + ")");

        // Crear y agregar sellos
        SelloDiscografico sello1 = new SelloDiscografico("Sony Music", "USA", 1929);
        SelloDiscografico sello2 = new SelloDiscografico("Universal Music", "USA", 1934);
        plataforma.agregarSello(sello1);
        plataforma.agregarSello(sello2);
        System.out.println("Sellos agregados: " + sello1.getNombre() + ", " + sello2.getNombre());

        // Crear artistas
        Artista artista1 = new Artista("Taylor Swift", "Pop", "USA");
        Artista artista2 = new Artista("Adele", "Pop", "UK");

        // Agregar artistas a sellos
        plataforma.agregarArtistaASello("Sony Music", artista1);
        plataforma.agregarArtistaASello("Universal Music", artista2);
        System.out.println("Artistas agregados a sellos.");

        // Crear álbumes
        Album album1 = new Album("1989", 2014, "Pop", 0);  // Cantidad inicial 0, se actualizará
        Album album2 = new Album("21", 2011, "Pop", 0);

        // Agregar álbumes a artistas
        plataforma.agregarAlbumAArtista("Taylor Swift", album1);
        plataforma.agregarAlbumAArtista("Adele", album2);
        System.out.println("Álbumes agregados a artistas.");

        // Crear canciones
        Cancion cancion1 = new Cancion("Shake It Off", 3.39, 1, 1000000);
        Cancion cancion2 = new Cancion("Blank Space", 3.51, 2, 1200000);
        Cancion cancion3 = new Cancion("Rolling in the Deep", 3.48, 1, 1500000);
        Cancion cancion4 = new Cancion("Someone Like You", 4.45, 2, 1400000);

        // Agregar canciones a álbumes usando el método del inciso d)
        ArrayList<Cancion> cancionesAlbum1 = new ArrayList<>();
        cancionesAlbum1.add(cancion1);
        cancionesAlbum1.add(cancion2);
        plataforma.agregarCancionesAlbum("Taylor Swift", cancionesAlbum1, "1989");

        ArrayList<Cancion> cancionesAlbum2 = new ArrayList<>();
        cancionesAlbum2.add(cancion3);
        cancionesAlbum2.add(cancion4);
        plataforma.agregarCancionesAlbum("Adele", cancionesAlbum2, "21");
        System.out.println("Canciones agregadas a álbumes.");

        // ---PRUEBA INCISO B: Canción más reproducida---
        LinkedList<Cancion> masReproducidas = plataforma.cancionMasReproducida();
        System.out.println("\nInciso B - Canción(es) más reproducida(s):");
        for (Cancion c : masReproducidas) {
            System.out.println("  " + c.getNombre() + " (" + c.getCantReproducciones() + " reproducciones)");
        }

        // ---PRUEBA INCISO C: Transferir artista---
        boolean transferido = plataforma.transferirArtistaDeSello("Taylor Swift", "Universal Music");
        System.out.println("\nInciso C - Transferencia de artista: " + (transferido ? "Exitosa" : "Fallida"));

        // ---PRUEBA INCISO D: Ya probado arriba al agregar canciones---

        // ---PRUEBA INCISO E: Lista de reproducción---
        LinkedList<ListaRep> pedidos = new LinkedList<>();
        pedidos.add(new ListaRep("Taylor Swift", 2));  // 2 canciones de Taylor
        pedidos.add(new ListaRep("Adele", 1));        // 1 canción de Adele
        Queue<Cancion> playlist = plataforma.obtenerListaReproduccion(pedidos);
        System.out.println("\nInciso E - Lista de reproducción:");
        while (!playlist.isEmpty()) {
            Cancion c = playlist.poll();
            System.out.println("  Reproduciendo: " + c.getNombre());
        }

        plataforma.imprimirArbol();
    }
}