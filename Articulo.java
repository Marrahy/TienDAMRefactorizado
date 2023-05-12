public class Articulo {

    //Enum
    public enum IVA {
        NORMAL,
        REDUCIDO,
        SUPERREDUCIDO
    }

    //Atributos
    private final String nombre;
    private double precio;
    private IVA tipoIVA;
    private int cantidad;

    //Constructor
    public Articulo(String nombre, double precio, IVA tipoIVA, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipoIVA = tipoIVA;
        this.cantidad = cantidad;
    }

    //Setters
    public void setTipoIVA(IVA tipoIVA) {
        this.tipoIVA = tipoIVA;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
    public void disminuir(int cantidad) {

    }

    public void aumentar(int cantidad) {

    }
}
