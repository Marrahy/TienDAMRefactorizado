import java.util.Scanner;

public class TienDAM {

    //Atributos
    private static Scanner input = new Scanner(System.in);
    private static Almacen gestionarAlmacen;
    private static boolean ejecutar = true;

    public static void main(String[] args) {
        
        System.out.println("¡Bienvenido a TienDAM!");
        while(ejecutar) {

        }
    }

    //Menus
    public void menuPrincipal() {
        System.out.println();
        System.out.println("1. Gestionar almacén.");
        System.out.println("2. Crear un pedido.");
        System.out.println("3. Salir.");
        System.out.print("Selecciona una opción: ");
    }

    public void menuAlmacen() {
        System.out.println();
        System.out.println("1. Mostrar artículos.");
        System.out.println("2. Buscar artículos.");
        System.out.println("3. Añadir artículos.");
        System.out.println("4. Recibir artículos.");
        System.out.println("5. Devovler artículos.");
        System.out.println("6. Salir");
        System.out.println("7. Selecciona una opción: ");
    }

    public void menuPedido() {
        System.out.println();
        System.out.println("1. Añadir a la cesta.");
        System.out.println("2. Quitar artículo de la cesta.");
        System.out.println("3. Modificar la cantidad de un artículo.");
        System.out.println("4. Aplicar descuento.");
        System.out.println("5. Realizar venta.");
        System.out.println("6. Salir");
        System.out.println("Selecciona una opción: ");
    }

    //

}