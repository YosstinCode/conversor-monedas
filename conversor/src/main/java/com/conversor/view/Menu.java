package com.conversor.view;

import java.util.List;
import java.util.Scanner;

import com.conversor.controller.Convertidor;

public class Menu {

    private List<String> monedas;
    private Scanner scanner;

    // Constructor
    public Menu(List<String> monedas) {
        this.monedas = monedas;
        this.scanner = new Scanner(System.in);
    }

    // Método de bienvenida
    public void mostrarBienvenida() {
        System.out.println("======================================");
        System.out.println("| Bienvenido al conversor de monedas |");
        System.out.println("======================================");
    }

    // Método para limpiar la pantalla
    public void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // Método para mostrar la lista de posibles monedas para convertir
    public void mostrarMonedas() {
        for (int i = 0; i < monedas.size(); i++) {
            System.out.println((i + 1) + ". " + monedas.get(i));
        }
        System.out.println("0. Salir");
    }

    // Método para leer la opción seleccionada por el usuario
    public int leerOpcion() {
        System.out.print("\nSelecciona una opción: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Por favor selecciona una opción: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Método para obtener una opción válida del usuario
    public Integer obtenerOpcion(String mensaje) {
        System.out.println(mensaje);
        mostrarMonedas();
        int opcion = leerOpcion();

        if (opcion == 0) {
            System.out.println("Saliendo...");
            return null;
        }

        if (opcion < 0 || opcion > monedas.size()) {
            System.out.println("Opción inválida");
            return null;
        }

        return opcion;
    }

    // Método para obtener la cantidad a convertir
    public Double obtenerCantidad() {
        System.out.print("\n- Por favor ingresa la cantidad a convertir: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Entrada inválida. Por favor ingresa la cantidad a convertir: ");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    // Método principal para mostrar el menú
    public void mostrarMenu() {
        while (true) {
            limpiarPantalla();
            mostrarBienvenida();

            Integer monedaActual = obtenerOpcion("\n- Por favor selecciona tu moneda actual: ");
            if (monedaActual == null) {
                break;
            }

            Double cantidad = obtenerCantidad();

            Integer monedaConvertir = obtenerOpcion("\n- Por favor selecciona la moneda a la que deseas convertir: ");
            if (monedaConvertir == null) {
                break;
            }

            String monedaOrigen = monedas.get(monedaActual - 1);
            String monedaDestino = monedas.get(monedaConvertir - 1);
            System.out.println("Convirtiendo de " + monedaOrigen + " a " + monedaDestino + "...");

            double resultado = Convertidor.convertir(monedaOrigen, monedaDestino, cantidad);

            if (resultado != -1) {
                System.out.println("Resultado: " + resultado);
            } else {
                System.out.println("Error al convertir la moneda");
            }

            System.out.print("Presiona Enter para continuar...");
            scanner.nextLine(); // Esperar a que el usuario presione Enter
            scanner.nextLine(); // Consumir la nueva línea pendiente
        }
    }
}