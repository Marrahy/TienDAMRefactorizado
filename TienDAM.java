import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TienDAM {

    //Atributos
    private static final Scanner input = new Scanner(System.in);
    private static Almacen gestionarAlmacen = new Almacen();
    private static Pedido pedido;
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
    }

    public void menuPedido() {
        System.out.println();
        System.out.println("1. Crear pedido.");
        System.out.println("2. Añadir artículo a la cesta.");
        System.out.println("3. Quitar artículo de la cesta.");
        System.out.println("4. Modificar la cantidad de un artículo.");
        System.out.println("5. Aplicar descuento.");
        System.out.println("6. Realizar venta");
        System.out.println("7. Salir.");
        System.out.println();
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
                System.out.print("Selecciona una opción: ");
                opcion = input.nextInt();
                if (opcion < min || opcion > max) {
                    System.out.println("Valor introducido no válido, escribe un valor entre " + min + " y " + max + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduce un entero entre " + min + " y " + max + ": ");
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

                if (gestionarAlmacen.articulos.isEmpty()) {
                    System.out.println("El almacén está vacío.");
                    break;
                }
                else if (posicion <= 0 || posicion > gestionarAlmacen.articulos.size()) {
                    System.out.println("Introduce un valor entre " + 1 + " y " + gestionarAlmacen.articulos.size());
                }
            } catch (Exception e) {
                System.out.println("Valor incorrecto, introduce un número entero mayor a 0 o menor a " + gestionarAlmacen.articulos.size());
                limpiarBufferScanner();
            }
        }
        return posicion;
    }

    //En base al input del usuario comprueba que la cantidad no sea menor a 1 o mayor al numero de articulos que hayan disponibles
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
            switch (cogerOpcion(1, 8)) {
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
                    gestionarAlmacen.mostrarArticulos();
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
        while(true) {
            menuPedido();
            switch (cogerOpcion(1, 7)) {
                case 1:
                    pedido = crearNuevoPedido();                  
                    break;
                case 2: {
                        gestionarAlmacen.mostrarArticulos();
                        int posicion = cogerPosicion();
                        int cantidad = cogerCantidad();
                        Articulo articulo = gestionarAlmacen.articulos.get(posicion - 1);
                        
                        if (gestionarAlmacen.articulos.get(posicion - 1).disminuir(cantidad)) {
                            pedido.agregarArticuloACesta(articulo);
                        } else {
                            System.out.println();
                            System.out.println("No se ha podido agregar el artículo a la cesta.");
                            System.out.println();
                            articulo = null;
                        }
                    }
                    break;
                case 3: {
                        if (pedido.getCesta().isEmpty()) {
                            System.out.println();
                            System.out.println("No hay artículos en la cesta.");
                            System.out.println();
                        } else {
                            pedido.mostrarCesta();
                            int posicion = cogerPosicion();
                            pedido.getCesta().remove(posicion - 1);
                            System.out.println();
                            System.out.println("Artículo eliminado de la cesta con éxito!");
                            System.out.println();
                            if (pedido.getCesta().isEmpty()) {
                                System.out.println("La cesta está vacía.");
                                System.out.println();
                            }
                        }
                    }
                    break;
                case 4: {
                        pedido.mostrarCesta();
                        int posicion = cogerPosicion();
                        int cantidad = cogerCantidad();
                        try {
                            pedido.modificarCantidad(cantidad, posicion - 1);
                        } catch (Exception e) {
                            System.out.println("No se ha podido modificar la cantidad de artículos de la cesta.");
                        }
                    }
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
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