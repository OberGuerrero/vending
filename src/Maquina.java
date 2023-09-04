import java.util.Scanner;

public class Maquina {
    public static void main(String[] args) {
        // Definir los productos y sus precios
        String[] productos = {"Paquete de gomas", "Choco chef", "Galletas Oreo", "Wafers Nucita", "Agua con gas", "Agua Natural", "Agua Manantial", "Jugo Tangelo", "Gaseosa Coca-cola"};
        int[] precios = {2300, 1200, 1200, 800, 1200, 1000, 1500, 2000, 2600};

        // Definir las denominaciones de billetes y monedas disponibles
        int[] denominacionesBilletes = {50000, 20000, 10000, 5000, 2000};
        int[] denominacionesMonedas = {1000, 500, 200, 100, 50};

        // Leer el valor del billete o moneda ingresado por el usuario
        Scanner scanner = new Scanner(System.in);
        int billete = 0;
        int moneda = 0;
        int saldo = 0;

        while (true) {
            System.out.print("Ingrese el dinero en pesos colombianos (0 para finalizar): ");
            int valor = scanner.nextInt();

            if (valor == 0) {
                break; // Si el usuario ingresa 0, finalizar el programa
            }

            // Validar si el valor ingresado es una denominación válida
            boolean esDenominacionValida = false;
            for (int denominacion : denominacionesBilletes) {
                if (valor == denominacion) {
                    esDenominacionValida = true;
                    billete += valor;
                    break;
                }
            }

            for (int denominacion : denominacionesMonedas) {
                if (valor == denominacion) {
                    esDenominacionValida = true;
                    moneda += valor;
                    break;
                }
            }

            if (!esDenominacionValida) {
                System.out.println("Denominación no válida, recuerde ingresar dinero con denominación Colombiana .");
            }

            saldo = billete + moneda;

            // Mostrar saldo actual
            System.out.println("Saldo actual: " + saldo + " pesos colombianos");
        }

        // Permitir al usuario seleccionar productos
        while (true) {
            System.out.println("Productos disponibles:");
            for (int i = 0; i < productos.length; i++) {
                System.out.println((i + 1) + ". " + productos[i] + " - Precio: " + precios[i] + " pesos");
            }

            System.out.print("Seleccione un producto (1-" + productos.length + ", 0 para finalizar): ");
            int opcion = scanner.nextInt();

            if (opcion == 0) {
                break; // Si el usuario selecciona 0, finalizar el programa
            }

            // Verificar si el saldo es suficiente para comprar el producto
            if (opcion >= 1 && opcion <= productos.length) {
                int precioProducto = precios[opcion - 1];
                if (saldo >= precioProducto) {
                    saldo -= precioProducto;
                    System.out.println("Producto '" + productos[opcion - 1] + "' comprado. Saldo restante: " + saldo + " pesos colombianos");
                } else {
                    System.out.print("Saldo insuficiente. ¿Desea ingresar más dinero para completar la cantidad? (s/n): ");
                    String respuesta = scanner.next();
                    if (respuesta.equalsIgnoreCase("s")) {
                        // Permitir al usuario ingresar solo denominaciones válidas
                        boolean esDenominacionValida = false;
                        while (!esDenominacionValida) {
                            System.out.print("Ingrese la cantidad adicional, recuerde ingresar en pesos colombianos (0 para finalizar): ");
                            int cantidadAdicional = scanner.nextInt();
                            for (int denominacion : denominacionesBilletes) {
                                if (cantidadAdicional == denominacion) {
                                    esDenominacionValida = true;
                                    saldo += cantidadAdicional;
                                    break;
                                }
                            }
                            for (int denominacion : denominacionesMonedas) {
                                if (cantidadAdicional == denominacion) {
                                    esDenominacionValida = true;
                                    saldo += cantidadAdicional;
                                    break;
                                }
                            }
                            if (!esDenominacionValida) {
                                System.out.println("Denominación no válida, ingrese un valor de billete o moneda válido.");
                            }
                        }
                    } else {
                        System.out.println("Por favor seleccione un producto correspondiente a la cantidad de dinero ingresada.");
                    }
                }
            } else {
                System.out.println("Opción no válida. Seleccione un número válido.");
            }
        }

        // Mostrar saldo final y finalizar el programa
        System.out.println("Saldo final: " + saldo + " pesos colombianos");
        
        // Calcular y mostrar el cambio en billetes y monedas al final
        System.out.println("El cambio a devolver es:");

        int cambio = saldo;

        /* Billetes
        for (int denominacion : denominacionesBilletes) {
            if (cambio >= denominacion) {
                int cantidad = cambio / denominacion;
                cambio %= denominacion;
                System.out.println(cantidad + " billetes de " + denominacion);
            }
        }*/

        // Monedas
        for (int denominacion : denominacionesMonedas) {
            if (cambio >= denominacion) {
                int cantidad = cambio / denominacion;
                cambio %= denominacion;
                System.out.println(cantidad + " monedas de " + denominacion);
            }
        }

        System.out.println("Gracias por usar la máquina de Vending UE. Hasta luego.");
    }
}
