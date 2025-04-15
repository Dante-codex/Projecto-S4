package com.mycompany.exp1_s5_dante_campos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Exp1_S5_Dante_Campos {

    public static void main(String[] args) {
        Scanner entradaUsuario = new Scanner(System.in);

        String[] tiposDeEntradas = {"VIP", "Platea Baja", "Platea Alta", "Palcos"};
        int[] preciosEstudiante = {20000, 10000, 9000, 6500};
        int[] preciosGeneral = {30000, 15000, 18000, 13000};
        Random generadorAleatorio = new Random();
        ArrayList<String> entradasVendidas = new ArrayList<>();
        
        while (true) {
            System.out.println("\n==============================");
            System.out.println("BIENVENIDO AL TEATRO MORO");
            System.out.println("==============================\n");
            System.out.println("1. Venta de entradas");
            System.out.println("2. Asientos disponibles");
            System.out.println("3. Promociones");
            System.out.println("4. Busqueda de entradas");
            System.out.println("5. Eliminar entrada");
            System.out.println("6. Salir");

            System.out.print("\nSeleccione una opcion (1-6): ");
            int opcion = entradaUsuario.nextInt();
            entradaUsuario.nextLine();

            switch (opcion) {
                case 1 -> ventaDeEntradas(entradaUsuario, tiposDeEntradas, preciosEstudiante, preciosGeneral, generadorAleatorio, entradasVendidas);
                case 2 -> asientosDisponibles(tiposDeEntradas, generadorAleatorio);
                case 3 -> mostrarPromociones(tiposDeEntradas, preciosEstudiante, preciosGeneral);
                case 4 -> buscarPorFolio(entradaUsuario, entradasVendidas);
                case 5 -> eliminarPorFolio(entradaUsuario, entradasVendidas);
                case 6 -> {
                    System.out.println("\nGracias por su visita. ¡Hasta la proxima!");
                    entradaUsuario.close();
                    return;
                }
                default -> System.out.println("Opcion invalida, seleccione un numero entre 1 y 6.");
            }
        }
    }

    private static void ventaDeEntradas(Scanner entradaUsuario, String[] tiposDeEntradas, int[] preciosEstudiante, int[] preciosGeneral, Random generadorAleatorio, ArrayList<String> entradasVendidas) {
        System.out.println("\n=== Venta de Entradas ===\n");
        for (int i = 0; i < tiposDeEntradas.length; i++) {
            System.out.println((i + 1) + ". " + tiposDeEntradas[i]);
            System.out.println("   Estudiante: $" + preciosEstudiante[i]);
            System.out.println("   General: $" + preciosGeneral[i]);
            System.out.println();
        }

        int indiceSeleccionadoEntrada;
        while (true) {
            try {
                System.out.print("Seleccione el tipo de entrada (1-4): ");
                indiceSeleccionadoEntrada = entradaUsuario.nextInt() - 1;
                entradaUsuario.nextLine();
                if (indiceSeleccionadoEntrada >= 0 && indiceSeleccionadoEntrada < tiposDeEntradas.length) {
                    break;
                } else {
                    System.out.println("Entrada invalida, seleccione un numero entre 1 y 4.");
                }
            } catch (Exception e) {
                System.out.println("Error: por favor ingrese un numero valido.");
                entradaUsuario.nextLine(); 
            }
        }
        System.out.print("Ingrese su edad: ");
        int edad = entradaUsuario.nextInt();
        entradaUsuario.nextLine();

        int precio = preciosGeneral[indiceSeleccionadoEntrada];
        int descuento = 0;

        if (edad < 18 || edad >= 60) {
            descuento = (int) (precio * (edad >= 60 ? 0.15 : 0.10));
        }

        int precioFinal = precio - descuento;
        int numeroTicket = generadorAleatorio.nextInt(10000) + 1;
        int numeroAsiento = generadorAleatorio.nextInt(100) + 1;

        String registro = "Folio: " + numeroTicket + ", Tipo: " + tiposDeEntradas[indiceSeleccionadoEntrada] +
                ", Precio Final: $" + precioFinal + ", Asiento: #" + numeroAsiento;
        entradasVendidas.add(registro);

        System.out.println("\nHa seleccionado una entrada " + tiposDeEntradas[indiceSeleccionadoEntrada] +
                ". Precio final: $" + precioFinal + " (Descuento: $" + descuento + ")");
        System.out.println("Folio de su ticket: " + numeroTicket);
        System.out.println("Asiento asignado: #" + numeroAsiento);
    }

    private static void asientosDisponibles(String[] tiposDeEntradas, Random generadorAleatorio) {
        System.out.println("\n=== Asientos Disponibles ===\n");
        for (String tipo : tiposDeEntradas) {
            System.out.println("\nSector: " + tipo);
            for (int i = 0; i < 5; i++) {
                int asiento = generadorAleatorio.nextInt(100) + 1;
                System.out.println("   Asiento: #" + asiento);
            }
        }
    }

    private static void mostrarPromociones(String[] tiposDeEntradas, int[] preciosEstudiante, int[] preciosGeneral) {
        System.out.println("\n=== Promociones ===\n");
        System.out.println("1. 10% de descuento para estudiantes.");
        System.out.println("2. 15% de descuento para tercera edad (60+).\n");
        System.out.println("Precios con descuento para cada entrada:\n");

        for (int i = 0; i < tiposDeEntradas.length; i++) {
            int precioEstudianteDescuento = (int) (preciosEstudiante[i] * 0.90);
            int precioTerceraEdadDescuento = (int) (preciosGeneral[i] * 0.85);
            System.out.println(tiposDeEntradas[i] + ":");
            System.out.println("   Estudiante: $" + precioEstudianteDescuento);
            System.out.println("   Tercera Edad: $" + precioTerceraEdadDescuento + "\n");
        }
    }

    private static void buscarPorFolio(Scanner entradaUsuario, ArrayList<String> entradasVendidas) {
        System.out.println("\n=== Busqueda por Folio ===\n");
        System.out.print("Ingrese el folio de la entrada: ");
        String folio = entradaUsuario.nextLine();

        boolean encontrado = false;
        for (String entrada : entradasVendidas) {
            if (entrada.contains("Folio: " + folio)) {
                System.out.println("\nDetalles de la entrada encontrada:");
                System.out.println(entrada);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("\nNo se encontraron entradas con el folio proporcionado.");
        }
    }

    private static void eliminarPorFolio(Scanner entradaUsuario, ArrayList<String> entradasVendidas) {
        System.out.println("\n=== Eliminar Entrada por Folio ===\n");
        System.out.print("Ingrese el folio de la entrada a eliminar: ");
        String folio = entradaUsuario.nextLine();

        boolean eliminado = false;
        for (int i = 0; i < entradasVendidas.size(); i++) {
            if (entradasVendidas.get(i).contains("Folio: " + folio)) {
                System.out.println("\nEntrada eliminada:");
                System.out.println(entradasVendidas.remove(i));
                eliminado = true;
                break;
            }
        }

        if (!eliminado) {
            System.out.println("\nNo se encontraron entradas con el folio proporcionado para eliminar.");
        }
    }
}
