import java.util.ArrayList;

public class Pedido {
    
    //Atributos
    private String nombreCliente;
    private double porcentajeDescuento;
    private double subtotal;
    private double precioFinal;
    private int IDTicket;

    private ArrayList<Articulo> cesta = new ArrayList<>();
    private ArrayList<Integer> articuloCantidad = new ArrayList<>();

    //Constructor
    public Pedido(String nombreCliente, int IDTicket) {
        setNombreCliente(nombreCliente);
        setIDTicket(IDTicket);
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
    public void setIDTicket(int IDTicket) {
        this.IDTicket = IDTicket;
    }
    public void setArticuloCantidad(ArrayList<Integer> articuloCantidad) {
        this.articuloCantidad = articuloCantidad;
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
    public int getIDTicket() {
        return IDTicket;
    }
    public ArrayList<Integer> getArticuloCantidad() {
        return articuloCantidad;
    }

    //Metodo para agregar un Articulo en la Array de articulos de Pedido
    public void agregarArticuloACesta(Articulo articulo) {
        cesta.add(articulo);
        System.out.println();
        System.out.println("Articulo " + articulo.getNombre() + " agregado a la cesta con éxito!");   
    }

    public void quitarArticuloDeLaCesta(int posicion) {
        cesta.remove(posicion);
        System.out.println();
        System.out.println("El artículo ha sido eliminado con éxito!");
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
