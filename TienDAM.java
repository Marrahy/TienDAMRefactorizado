import java.util.InputMismatchException;
import java.util.Scanner;

public class TienDAM {

    //Atributos
    private final Scanner input = new Scanner(System.in);
    private static final Almacen gestionarAlmacen = new Almacen();
    private Pedido pedido;
    private static boolean ejecutar = true;
    private int numeroDeArticulos;

    //Main
    public static void main(String[] args) {
        TienDAM inicioTienda = new TienDAM();

        System.out.println("¡Bienvenido a TienDAM!");
        gestionarAlmacen.cargarArticulosDeEjemplo();

        while (ejecutar) {
            inicioTienda.inicio();
        }
    }

    //Inicia el programa mostrando el menu principal
    public void inicio() {
        menuPrincipal();
        switch (cogerOpcion(1, 3)) {
            case 1 -> gestionAlmacen();
            case 2 -> gestionPedido();
            case 3 -> {
                System.out.println("Taluego!");
                ejecutar = false;
            }
            default -> {
            }
        }
    }

    //Menu principal
    public void menuPrincipal() {
        System.out.println();
        System.out.println("1. Gestionar almacén.");
        System.out.println("2. Gestionar un pedido.");
        System.out.println("3. Salir.");
        System.out.println();
    }

    //Menu de almacen
    public void menuAlmacen() {
        System.out.println();
        System.out.println("Menú almacén seleccionado.");
        System.out.println();
        System.out.println("1. Mostrar artículos.");
        System.out.println("2. Buscar artículos.");
        System.out.println("3. Añadir artículos.");
        System.out.println("4. Quitar artículo.");
        System.out.println("5. Modificar precio del artículo.");
        System.out.println("6. Recibir artículos.");
        System.out.println("7. Devovler artículos.");
        System.out.println("8. Atrás.");
        System.out.println();
    }

    //Menu de pedido
    public void menuPedido() {
        System.out.println();
        System.out.println("Menú pedido seleccionado.");
        System.out.println();
        System.out.println("1. Crear pedido.");
        System.out.println("2. Añadir artículo a la cesta.");
        System.out.println("3. Quitar artículo de la cesta.");
        System.out.println("4. Modificar la cantidad de un artículo.");
        System.out.println("5. Mostrar cesta.");
        System.out.println("6. Realizar venta.");
        System.out.println("7. Atrás.");
        System.out.println();
    }

    //-------------------------------------------------------Metodos--------------------------------------------------\\

    //Limpia el buffer del scanner
    public void limpiarBufferScanner() {
        input.nextLine();
    }

    //En base al input del usuario comprueba si el valor es valido entre los parametros pasados
    public int cogerOpcion(int min, int max) throws InputMismatchException {
        int opcion = 0;
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

    //Indica el tipo de IVA
    public Articulo.IVA verificarIVA(int opcion) {
        return switch (opcion) {
            case 1 -> Articulo.IVA.NORMAL;
            case 2 -> Articulo.IVA.REDUCIDO;
            case 3 -> Articulo.IVA.SUPERREDUCIDO;
            default -> Articulo.IVA.NULL;
        };
    }

    //Crea un atículo y lo almacena en la ArrayList de articulos en almacen.
    public Articulo crearNuevoArticulo() {
        limpiarBufferScanner();

        System.out.println();
        System.out.print("Escribe el nombre del artículo: ");
        String nombre = input.nextLine();

        //Declaro la variable "precio" a 0 para loopear el codigo hasta recbir un precio valido
        double precio = 0;
        while (precio <= 0) {
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
    public int cogerPosicion() {
        int posicion = 0;
        while (posicion <= 0 || posicion > gestionarAlmacen.articulos.size()) {
            try {
                limpiarBufferScanner();
                System.out.print("Introduce el número de artículo: ");
                posicion = input.nextInt();

                if (gestionarAlmacen.articulos.isEmpty()) {
                    System.out.println("El almacén está vacío.");
                    break;
                } else if (posicion <= 0 || posicion > gestionarAlmacen.articulos.size()) {
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

    //En base al input del usuario verifico que el valor pasado sea valido
    public double cogerDouble() {
        limpiarBufferScanner();
        double decimal = 0;
        while (decimal <= 0) {
            try {
                decimal = input.nextDouble();
            } catch (Exception e) {
                System.out.println();
                System.out.println("Valor decimal erróneo, introduce un valor válido.");
                break;
            }
        }
        limpiarBufferScanner();
        return decimal;
    }

    //Metodo para gestionar todas las opciones que ofrece el metodo menuAlmacen()
    public void gestionAlmacen() {

        //Variables locales del metodo
        int posicion;
        int cantidad;
        while (true) {
            menuAlmacen();
            switch (cogerOpcion(1, 8)) {
                case 1 -> gestionarAlmacen.mostrarArticulos();
                case 2 -> {
                    limpiarBufferScanner();
                    System.out.print("Nombre del artículo: ");
                    String nombre = input.nextLine();
                    gestionarAlmacen.buscarArticulo(nombre);
                }
                case 3 -> {
                    Articulo nuevoArticulo = crearNuevoArticulo();
                    gestionarAlmacen.agregarArticulo(nuevoArticulo);
                }
                case 4 -> {
                    gestionarAlmacen.mostrarArticulos();
                    posicion = cogerPosicion();
                    gestionarAlmacen.quitarArticulo(posicion);
                }
                case 5 -> {
                    posicion = cogerPosicion();
                    System.out.print("Introduce el precio del artículo: ");
                    double precio = input.nextDouble();
                    gestionarAlmacen.modificarPrecio(posicion, precio);
                }
                case 6 -> {
                    posicion = cogerPosicion();
                    cantidad = cogerCantidad();
                    gestionarAlmacen.recibir(posicion, cantidad);
                }
                case 7 -> {
                    posicion = cogerPosicion();
                    cantidad = cogerCantidad();
                    gestionarAlmacen.devolver(posicion, cantidad);
                }
                case 8 -> {
                    return;
                }
            }
        }
    }

    //Metodo que gestiona todas las opciones que ofrece el menu gestionPedido()
    public void gestionPedido() {
        int posicion;
        int cantidad;
        while (true) {
            menuPedido();
            switch (cogerOpcion(1, 8)) {
                case 1 -> pedido = crearNuevoPedido();
                case 2 -> {
                    if (pedidoEsNull(pedido)) {
                        gestionarAlmacen.mostrarArticulos();
                        posicion = cogerPosicion();
                        cantidad = cogerCantidad();
                        Articulo articulo = gestionarAlmacen.articulos.get(posicion - 1);

                        if (gestionarAlmacen.articulos.get(posicion - 1).disminuir(cantidad)) {
                            pedido.getArticuloCantidad().add(cantidad);
                            pedido.agregarArticuloACesta(articulo);
                        } else {
                            System.out.println();
                            System.out.println("No se ha podido agregar el artículo a la cesta.");
                            System.out.println();
                            articulo = null;
                        }
                    }
                }
                case 3 -> {
                    if (pedidoEsNull(pedido)) {
                        if (pedido.getCesta().isEmpty()) {
                            System.out.println();
                            System.out.println("No hay artículos en la cesta.");
                        } else {
                            mostrarCesta();
                            posicion = cogerPosicion();
                            gestionarAlmacen.devolverArticulosDePedido(pedido.getArticuloCantidad(), pedido.getCesta());
                            numeroDeArticulos--;
                            pedido.quitarArticuloDeLaCesta(posicion - 1);
                            System.out.println();
                            System.out.println("Artículo eliminado de la cesta con éxito!");
                            System.out.println();
                            if (pedido.getCesta().isEmpty()) {
                                System.out.println("La cesta está vacía.");
                            }
                        }
                    }
                }
                case 4 -> {
                    if (pedidoEsNull(pedido)) {
                        mostrarCesta();
                        posicion = cogerPosicion();
                        cantidad = cogerCantidad();
                        try {
                            pedido.modificarCantidad(cantidad, posicion - 1);
                        } catch (Exception e) {
                            System.out.println();
                            System.out.println("No se ha podido modificar la cantidad de artículos de la cesta.");
                        }
                    }
                }
                case 5 -> {
                    if (pedidoEsNull(pedido)) {
                        mostrarCesta();
                    }
                }
                case 6 -> {
                    if (pedidoEsNull(pedido)) {
                        System.out.println();
                        System.out.println("<------------------------------------------------------------------->");
                        System.out.println("Nombre del cliente: " + pedido.getNombreCliente() + "       " + "ID del Ticket: " + pedido.getIDTicket());
                        for (int i = 0; i < pedido.getCesta().size(); i++) {
                            System.out.println();
                            System.out.println("Nombre: " + pedido.getCesta().get(i).getNombre() + "    " + "Precio: " + pedido.getCesta().get(i).getPrecio() + "$");
                            System.out.println("IVA: " + pedido.getCesta().get(i).getIVA());
                            System.out.println("Cantidad: " + pedido.getArticuloCantidad().get(i));
                            System.out.println();
                        }
                        System.out.println();
                        System.out.println("Precio de la compra sin IVA: " + calcularSubtotal() + "$" + "       " + "Precio final IVA incluido: " + (calcularPrecioFinal() / ((aplicarDescuento() / 100) + 1)) + "$");
                        System.out.println("<------------------------------------------------------------------->");
                        System.out.println();
                        System.out.println("¡Gracias por tu visita " + pedido.getNombreCliente() + ", vuelve pronto!");
                        menuPedido();
                        limpiarBufferScanner();
                        pedido = null;
                    }
                }
                case 7 -> {
                    return;
                }
            }
        }
    }

    //Metodo para crear un pedido nuevo o para reemplazar uno existente
    public Pedido crearNuevoPedido() {
        limpiarBufferScanner();
        if (pedido != null) {
            System.out.println("¿Estas seguro de querer reemplaza el pedido actual y crear uno nuevo?");
            System.out.println("Escribe 'SI' si quieres reemplazarlo por otro pedido, escribe 'NO' para volver atrás.");
            String accion = input.nextLine();
            switch (accion) {
                case "SI" -> pedido = null;
                case "NO" -> {
                    System.out.println();
                    System.out.println("Has vuelto al menú de gestion de pedido.");
                    gestionPedido();
                }
            }
        }
        System.out.print("Nombre del cliente: ");
        String nombreCliente = input.nextLine();

        int IDTicket = (int) (Math.random() * (999999 - 100000) + 100000);

        System.out.println();
        System.out.println("Pedido creado!");

        return new Pedido(nombreCliente, IDTicket);
    }

    public void mostrarCesta() {
        if (gestionarAlmacen.articulos.isEmpty()) {
            System.out.println("No hay ningún artículo en la cesta actualmente.");
            System.out.println();
        } else {
            numeroDeArticulos = 0;
            for (Articulo cesta : pedido.getCesta()) {
                System.out.println("--------------------------------");
                System.out.println("Artículo número --> " + (numeroDeArticulos + 1));
                System.out.println("Nombre: " + cesta.getNombre());
                System.out.println("Precio: " + cesta.getPrecio() + "$");
                System.out.println("IVA: " + cesta.getIVA());
                System.out.println("Cantidad: " + pedido.getArticuloCantidad().get(numeroDeArticulos));
                System.out.println("--------------------------------");
                numeroDeArticulos++;
            }
        }
    }


    public boolean pedidoEsNull(Pedido pedido) {
        if (pedido == null) {
            System.out.println();
            System.out.println("No hay ningún pedido en proceso, crea uno para poder gestionarlo.");
            return false;
        } else {
            return true;
        }
    }

    public double aplicarDescuento() {
        System.out.print("Introduce un porcentaje de descuento: ");
        System.out.println();
        return cogerDouble();
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < pedido.getCesta().size(); i++) {
            subtotal += pedido.getCesta().get(i).getPrecio() * pedido.getArticuloCantidad().get(i);
        }
        return subtotal;
    }

    public double calcularPrecioFinal() {
        double precioFinal = 0;
        for (int i = 0; i < pedido.getCesta().size(); i++) {

            if (pedido.getCesta().get(i).getIVA() == Articulo.IVA.NORMAL) {
                precioFinal += (pedido.getCesta().get(i).getPrecio() * pedido.getArticuloCantidad().get(i)) * 1.21;

            } else if (pedido.getCesta().get(i).getIVA() == Articulo.IVA.REDUCIDO) {
                precioFinal += (pedido.getCesta().get(i).getPrecio() * pedido.getArticuloCantidad().get(i)) * 1.10;

            } else {
                precioFinal += (pedido.getCesta().get(i).getPrecio() * pedido.getArticuloCantidad().get(i)) * 1.04;
            }
        }
        return precioFinal;
    }
}