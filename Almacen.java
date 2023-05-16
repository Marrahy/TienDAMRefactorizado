import java.util.ArrayList;

public class Almacen {
    
    //Atributos
    ArrayList<Articulo> articulos = new ArrayList<>();

    //Constructor vacio
    public Almacen() {

    }

    //Metodo para generar articulos dentro de la ArrayList como ejemplos
    public void cargarArticulosDeEjemplo() {
        Articulo boli = new Articulo("Boli", 0.80, Articulo.IVA.NORMAL, 100);
        Articulo platano = new Articulo("Plátano", 0.25, Articulo.IVA.REDUCIDO, 50);
        Articulo monitor = new Articulo("Monitor", 150, Articulo.IVA.SUPERREDUCIDO, 250);
        Articulo teclado = new Articulo("Teclado", 50, Articulo.IVA.SUPERREDUCIDO, 2);
        Articulo ordenador = new Articulo("Ordenador", 800, Articulo.IVA.NORMAL, 5);

        articulos.add(boli);
        articulos.add(platano);
        articulos.add(monitor);
        articulos.add(teclado);
        articulos.add(ordenador);
    }

    //Imprime todos los articulos de la Array de articulos
    public void mostrarArticulos() {
        System.out.println();
        if (articulos.isEmpty()) {
            System.out.println("El almacén está vacío.");
        }
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

    //Agrega el Articulo creado a la Array de articulos de Almacen
    public void agregarArticulo(Articulo articulo) {
        articulos.add(articulo);
        System.out.println("Se ha añadido el artículo con éxito!");
    }

    //Elimina el Articulo de la Array de articulos de Almacen
    public void quitarArticulo(int posicion) {
        System.out.println("El artículo " + articulos.get(posicion - 1).getNombre() + " se ha eliminado.");
        articulos.remove(posicion - 1);
    }

    //Modifica el precio del Articulo de la Array de articulos con la posicion pasada como parametro
    public void modificarPrecio(int posicion, double precio) {
        for (int i = 0; i < articulos.size(); i++) {
            if (i == posicion) {
                Articulo modificarPrecioArticulo = articulos.get(i);
                modificarPrecioArticulo.setPrecio(precio);
            }
        }
    }

    //En base al nombre pasado en el parametro itera por toda la ArrayList de articulos y printea los articulos que contenga esa String

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

    //Dados los parametros pasados con los metodos cogerPosicion() y cogerCantidad() aumenta la cantidad del articulo seleccionado
    public void recibir(int posicion, int cantidad) {
        try {
            articulos.get(posicion).aumentar(cantidad);
        } catch (Exception e) {
            System.out.println("Introduce un valor válido.");
        }
        System.out.println("Se han recibido: " + cantidad + "del artículo " + articulos.get(posicion).getNombre() + ".");
    }

    //Dados los parametros pasados con los metodos cogerPosicion() y cogerCantidad() disminuye la cantidad del articulo seleccionado
    public void devolver(int posicion, int cantidad) {
        try {
            articulos.get(posicion).disminuir(cantidad);
        } catch (Exception e) {
            System.out.println("Introduce un valor válido.");
        }
        System.out.println("Se han retirado: " + cantidad + "del artículo " + articulos.get(posicion).getNombre() + ".");
    }

    //Metodo para devolver las unidades de un Articulo que este agregado a la Array de articulos de Pedido
    public void devolverArticulosDePedido(ArrayList<Integer> articuloCantidad, ArrayList<Articulo> pedido) {
        for (int i = 0; i < articuloCantidad.size(); i++) {
            if (articulos.get(i).getNombre().equals(pedido.get(i).getNombre())) {
                articulos.get(i).aumentar(articuloCantidad.get(i));
            }
        }
    }
}
