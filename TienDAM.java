import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TienDAM {

    //Atributos
    private static final Scanner input = new Scanner(System.in);
    private static final Almacen gestionarAlmacen = new Almacen();
    private static boolean ejecutar = true;

    public static void main(String[] args) {
        TienDAM inicioTienda = new TienDAM();
        System.out.println("¡Bienvenido a TienDAM!");
        gestionarAlmacen.cargarArticulosDeEjemplo();
        while(ejecutar) {
            inicioTienda.inicio();
        }
    }

    //Menus
    public void menuPrincipal() {
        System.out.println();
        System.out.println("1. Gestionar almacén.");
        System.out.println("2. Crear un pedido.");
        System.out.println("3. Salir.");
        System.out.println();
        System.out.print("Selecciona una opción: ");
    }

    public void menuAlmacen() {
        System.out.println();
        System.out.println("Almacén seleccionado.");
        System.out.println();
        System.out.println("1. Mostrar artículos.");
        System.out.println("2. Buscar artículos.");
        System.out.println("3. Añadir artículos.");
        System.out.println("4. Quitar artículo.");
        System.out.println("5. Modificar precio del artículo");
        System.out.println("6. Recibir artículos.");
        System.out.println("7. Devovler artículos.");
        System.out.println("8. Salir");
        System.out.println();
        System.out.print("Selecciona una opción: ");
    }

    public void menuPedido() {
        System.out.println();
        System.out.println("Pedido seleccionado.");
        System.out.println();
        System.out.println("1. Añadir a la cesta.");
        System.out.println("2. Quitar artículo de la cesta.");
        System.out.println("3. Modificar la cantidad de un artículo.");
        System.out.println("4. Aplicar descuento.");
        System.out.println("5. Realizar venta.");
        System.out.println("6. Salir");
        System.out.println();
        System.out.print("Selecciona una opción: ");
    }

    //Metodos
    public void limpiarBufferScanner() {
        input.nextLine();
    }

    //En base al input del usuario comprueba si el valor es válido.
    public int cogerOpcion(int min, int max) throws InputMismatchException{
        int opcion = -1;
        while (opcion < min || opcion > max) {
            try {
                opcion = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Introduce un valor entero.");
                limpiarBufferScanner();
            } catch (Exception e) {
                System.out.println("Valor introducido no válido, escribe un valor entre " + min + " y " + max + ".");
                limpiarBufferScanner();
            }
        }
        return opcion;
    }

    //Indica el tipo de IVA.
    public Articulo.IVA verificarIVA(int opcion) {
        switch (opcion) {
            case 1:
                return Articulo.IVA.NORMAL;
            case 2:
                return Articulo.IVA.REDUCIDO;
            case 3:
                return Articulo.IVA.SUPERREDUCIDO;
        }
        return Articulo.IVA.NULL;
    }

    //Crea un atículo y lo almacena en la ArrayList de articulos en almacen.
    public Articulo crearNuevoArticulo() {
        limpiarBufferScanner();

        System.out.println();

        System.out.print("Escribe el nombre del artículo: ");
        String nombre = input.nextLine();

        double precio = -1;
        while (precio == -1) {
            try {
                System.out.print("Fija un precio al artículo: ");
                precio = input.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Introduce un valor entero o decimal.");
                limpiarBufferScanner();
            }
        }

        System.out.println("Tipos de IVA: ");
        System.out.println("1. Normal (21%)");
        System.out.println("2. Reducido (10%)");
        System.out.println("3. Superreducido (4%)");
        System.out.print("Selecciona el tipo de IVA: ");

        int opcion = cogerOpcion(1, 3);
        Articulo.IVA tipoIVA = verificarIVA(opcion);

        System.out.print("Introduce una cantidad: ");
        int cantidad = input.nextInt();

        System.out.println("Artículo almacenado con éxito!");

        return new Articulo(nombre, precio, tipoIVA, cantidad);
    }

    //En base al input del usuario comprueba que la posicion no sea menor a 0 ni mayor a la longitud de la array de articulos
    public int cogerPosicion(){
        int posicion = -1;
        while (posicion <= 0 || posicion > gestionarAlmacen.articulos.size()) {
            try {
                System.out.print("Introduce el número de artículo: ");
                posicion = input.nextInt();
            } catch (Exception e) {
                System.out.println("Valor incorrecto, introduce un número entero mayor a 0 o menor al número de artículos dsiponibles.");
                limpiarBufferScanner();
            }
        }
        return posicion - 1;
    }

    //En base al input del usuario comprueba que la cantidad no sea menor a 1
    public int cogerCantidad() {
        int cantidad = -1;
        while (cantidad <= 0) {
            try {
                System.out.print("Cantidad de artículos: ");
                cantidad = input.nextInt();
            } catch (Exception e) {
                System.out.println("Introduce un valor válido (mayor a 0).");
                limpiarBufferScanner();
            }
        }
        return cantidad;
    }

    //Metodo para gestionar todas las opciones que ofrece el metodo menuAlmacen()
    public void gestionAlmacen() {
        while (true) {
            menuAlmacen();
            switch (cogerOpcion(1, 5)) {
                case 1:
                    gestionarAlmacen.mostrarArticulos();
                    break;
                case 2:
                    limpiarBufferScanner();
                    System.out.print("Nombre del artículo: ");
                    String nombre = input.nextLine();
                    gestionarAlmacen.buscarArticulo(nombre);
                    break;
                case 3:
                    Articulo nuevoArticulo = crearNuevoArticulo();
                    gestionarAlmacen.agregarArticulo(nuevoArticulo);
                    break;
                case 4: {
                    int posicion = cogerPosicion();
                    gestionarAlmacen.quitarArticulo(posicion);
                }
                    break;
                case 5: {
                    int posicion = cogerPosicion();
                    System.out.print("Introduce el precio del artículo: ");
                    double precio = input.nextDouble();
                    gestionarAlmacen.modificarPrecio(posicion, precio);
                }
                    break;
                case 6: {
                    int posicion = cogerPosicion();
                    int cantidad = cogerCantidad();
                    gestionarAlmacen.recibir(posicion, cantidad);
                }
                    break;
                case 7: {
                    int posicion = cogerPosicion();
                    int cantidad = cogerCantidad();
                    gestionarAlmacen.devolver(posicion, cantidad);
                }
                    break;
                case 8:
                    return;
            }
        }
    }

    public Pedido crearNuevoPedido() {
        limpiarBufferScanner();
        System.out.print("Nombre del cliente: ");
        String nombreCliente = input.nextLine();

        ArrayList<Articulo> cesta = new ArrayList<>();

        int IDticket = (int)(Math.random() * (999999 + 100000) - 100000);

        System.out.println("Pedido creado!");

        return new Pedido(nombreCliente, cesta, 0, 0, 0, IDticket);
    }

    //Metodo que gestiona todas las opciones que ofrece el menu gestionPedido()
    public void gestionPedido() {
        Pedido nuevoPedido = crearNuevoPedido();
        while(true) {
            menuPedido();
            switch (cogerOpcion(1, 5)) {
                case 1:
                    gestionarAlmacen.mostrarArticulos();
                    int posicion = cogerPosicion();
                    int cantidad = cogerCantidad();

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    return;
            }
        }
    }

    public double aplicarDescuento() {
        System.out.println("Introduce un valor de descuento: ");
        return input.nextDouble();
    }

    public double calcularSubtotal() {
        double subtotal = 0.0;
        //for (int i = 0; i < ; i++) {

        //}
        return subtotal;
    }

    public double calcularPrecioFinal() {
        return 0.0;
    }


    //Inicia el programa mostrando el menu principal
    public void inicio() {
        menuPrincipal();
        switch (cogerOpcion(1, 3)) {
            case 1:
                gestionAlmacen();
            break;
            case 2:
                gestionPedido();
            break;
            case 3:
                System.out.println("Taluego!");
                ejecutar = false;
            break;
            default:
                break;
        }
    }

}