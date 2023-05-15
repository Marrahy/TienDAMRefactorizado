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
}
