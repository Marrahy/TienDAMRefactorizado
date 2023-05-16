import java.util.InputMismatchException;

public class Articulo {

    //Enum
    public enum IVA {
        NULL,
        NORMAL,
        REDUCIDO,
        SUPERREDUCIDO
    }

    //Atributos
    private String nombre;
    private double precio;
    private IVA tipoIVA;
    private int cantidad;

    //Constructor
    public Articulo(String nombre, double precio, IVA tipoIVA, int cantidad) {
        setNombre(nombre);
        setPrecio(precio);
        setTipoIVA(tipoIVA);
        setCantidad(cantidad);
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTipoIVA(IVA tipoIVA) {
        this.tipoIVA = tipoIVA;
    }
    public void setCantidad(int cantidad){
        if (cantidad > 0) {
            this.cantidad = cantidad;
        } else {
            System.out.println("La cantidada de artículos no puede ser negativa");
        }
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public IVA getIVA() {
        return tipoIVA;
    }
    public int getCantidad() {
        return cantidad;
    }

    //Metodos
    public boolean disminuir(int cantidad)throws InputMismatchException {
        try {
            if (cantidad <= this.cantidad) {
                this.cantidad -= cantidad;
                return true;
            } else {
                System.out.print("No hay artículos disponibles, introduce una cantidad más pequeña.");
                System.out.println();
                return false;
            }
        } catch (InputMismatchException e) {
            System.out.println("Introduce un valor entero.");
            return false;
        }    
    }

    public void aumentar(int cantidad)throws Exception {
        this.cantidad += cantidad;
    }
}
