import java.util.ArrayList;

public class Almacen {
    
    //Atributos
    ArrayList<Articulo> articulos = new ArrayList<>();

    //Constructor
    public Almacen() {

    }
    
    //Metodos
    public boolean añadirArticulo(Articulo articulo) {
        return true;
    }

    public boolean añadirArticulo(String nombre, double precio, Articulo.IVA tipoIVA, int cantidad) {
        return true;
    }

    public boolean quitarArticulo(int posicion) {
        return true;
    }

    public boolean modificarPrecio(int posicion, int cantidad) {
        return true;
    }

    public void buscarArticulo(String nombre) {

    }

    public boolean recibir(int posicion, int cantidad) {
        return true;   
    }

    public boolean devolver(int posicion, int cantidad) {
        return true;
    }

}
