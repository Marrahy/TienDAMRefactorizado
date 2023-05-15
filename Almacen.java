import java.nio.file.LinkPermission;
import java.util.ArrayList;

public class Almacen {
    
    //Atributos
    ArrayList<Articulo> articulos = new ArrayList<>();

    //Constructor vacio
    public Almacen() {

    }

    //Metodos

    public void cargarArticulosDeEjemplo() {
        Articulo boli = new Articulo("Boli", 0.80, Articulo.IVA.NORMAL, 100);
        Articulo platano = new Articulo("Plátano", 0.25, Articulo.IVA.REDUCIDO, 0);
        Articulo monitor = new Articulo("Monitor", 150, Articulo.IVA.SUPERREDUCIDO, 250);
        Articulo teclado = new Articulo("Teclado", 50, Articulo.IVA.SUPERREDUCIDO, 2);
        Articulo ordenador = new Articulo("Ordenador", 800, Articulo.IVA.NORMAL, 5);

        articulos.add(boli);
        articulos.add(platano);
        articulos.add(monitor);
        articulos.add(teclado);
        articulos.add(ordenador);
    }
    public void mostrarArticulos() {
        System.out.println();
        int numeroArticulo = 1;
        for (Articulo articulo: articulos) {
            System.out.println("--------------------------------");
            System.out.println("Artículo número --> " + numeroArticulo);
            System.out.println("Nombre: " + articulo.getNombre());
            System.out.println("Precio: " + articulo.getPrecio() + "$");
            System.out.println("IVA: " + articulo.getIVA());
            System.out.println("Cantidad: " + articulo.getCantidad());
            System.out.println("--------------------------------");
            numeroArticulo++;
        }
        System.out.println();
    }
    public void agregarArticulo(Articulo articulo) {
        articulos.add(articulo);
        System.out.println("Se ha añadido el artículo con éxito!");
    }

    public void quitarArticulo(int posicion) {
        articulos.remove(posicion);
        System.out.println("El artículo " + articulos.get(posicion).getNombre() + " se ha eliminado.");
    }

    public void modificarPrecio(int posicion, double precio) {
        for (int i = 0; i < articulos.size(); i++) {
            if (i == posicion) {
                Articulo modificarPrecioArticulo = articulos.get(i);
                modificarPrecioArticulo.setPrecio(precio);
            }
        }
    }

    public void buscarArticulo(String nombre) {
        for (Articulo articulo: articulos) {
            if (articulo.getNombre().contains(nombre)) {
                System.out.println("--------------------------------");
                System.out.println("Nombre: " + articulo.getNombre());
                System.out.println("Precio: " + articulo.getPrecio() + "$");
                System.out.println("IVA: " + articulo.getIVA());
                System.out.println("Cantidad: " + articulo.getCantidad());
                System.out.println("--------------------------------");
            } else {
                System.out.println("No se han encontrado resultados.");
            }
        }
    }

    public void recibir(int posicion, int cantidad) {
        articulos.get(posicion).disminuir(cantidad);
        System.out.println("Se han retirado: " + cantidad + "del artículo " + articulos.get(posicion).getNombre() + ".");
    }

    public void devolver(int posicion, int cantidad) {
        articulos.get(posicion).aumentar(cantidad);
        System.out.println("Se han introducido: " + cantidad + "del artículo " + articulos.get(posicion).getNombre() + ".");
    }
}
