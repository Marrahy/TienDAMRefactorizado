import java.util.ArrayList;

public class Pedido {
    
    //Atributos
    private String nombreCliente;
    private double porcentajeDescuento;
    private double subtotal;
    private double precioFinal;
    private int IDticket;

    private ArrayList<Articulo> cesta = new ArrayList<>();

    //Constructor
    public Pedido(String nombreCliente, ArrayList<Articulo> cesta ,double porcentajeDescuento, double subtotal, double precioFinal, int IDticket) {
        setNombreCliente(nombreCliente);
        setCesta(cesta);
        setPorcentajeDescuento(porcentajeDescuento);
        setSubtotal(subtotal);
        setPrecioFinal(precioFinal);
        setIDticket(IDticket);
    }

    //Constructor vacio
    public Pedido() {

    }

    //Setters
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    public void setCesta(ArrayList<Articulo> cesta) {
        this.cesta = cesta;
    }
    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }
    public void setIDticket(int IDticket) {
        this.IDticket = IDticket;
    }

    //Getters
    public String getNombreCliente() {
        return nombreCliente;
    }
    public ArrayList<Articulo> getCesta() {
        return cesta;
    }
    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public double getPrecioFinal() {
        return precioFinal;
    }
    public int getIDticket() {
        return IDticket;
    }

    //Metodos
    public void agregarArticuloACesta(Articulo articulo) {
        cesta.add(articulo);
        System.out.println();
        System.out.println("Articulo " + articulo.getNombre() + " agregado a la cesta con éxito!");   
    }

    public void quitarArticuloDeLaCesta(int posicion) {
        cesta.remove(posicion);
        System.out.println("El artículo ha sido eliminado con éxito!");
    }

    public void mostrarCesta() {
        int numeroArticulo = 1;
        for (Articulo cesta : cesta) {
            System.out.println("--------------------------------");
            System.out.println("Artículo número --> " + numeroArticulo);
            System.out.println("Nombre: " + cesta.getNombre());
            System.out.println("Precio: " + cesta.getPrecio() + "$");
            System.out.println("IVA: " + cesta.getIVA());
            System.out.println("Cantidad: " + cesta.getCantidad());
            System.out.println("--------------------------------");
            numeroArticulo++;
        }
    }

    public void modificarCantidad(int cantidad, int posicion)throws Exception {
        if (cantidad <= cesta.get(posicion).getCantidad()) {
            cesta.get(posicion).setCantidad(cantidad);
        } else {
            System.out.println();
            System.out.println("La cantidad es mayor al número de artículos existentes.");
        }
    }
}
