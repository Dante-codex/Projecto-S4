package com.mycompany.exp1_s4_dante_campos;

/**
 *
 * @author Dante Campos
 * 07-04-2025
 */

import java.util.Scanner;

public class Exp1_S4_Dante_Campos {

    /**
 * Script centrado en cumplir los requerimientos del trabajo 2 de la clase programacion 1
 * Permite al usuario seleccionar el tipo de entrada y tarifa, y calcular el precio total.
     * @param args
 */
    
    public static void main(String[] args) {
         // Definicion de variables
        Scanner entradaUsuario = new Scanner(System.in);
         // Captura entrada de usuario para posteriormente utilizarlo
        String[] tiposDeEntradas = {"VIP", "Platea Baja", "Platea Alta", "Palcos"};
        double[] preciosEstudiante = {20000, 10000, 9000, 6500};
        double[] preciosGeneral = {30000, 15000, 18000, 13000};
        String[] zonasTeatro = {"Zona A", "Zona B", "Zona C"};
        // Output de bienvenida
        while (true) {
            // Menu principal
            System.out.println("\n==============================");
            System.out.println("BIENVENIDO AL TEATRO MORO");
            System.out.println("==============================\n");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Salir");

            // Captura de opcion del usuario
            System.out.print("\nSeleccione una opcion (1-2): ");
            int opcion = entradaUsuario.nextInt();
            entradaUsuario.nextLine();

            if (opcion == 2) {
                System.out.println("\nGracias por su visita. Hasta la proxima!");
                break;
            } else if (opcion == 1) {
                // Output de opciones
                System.out.println("\n=== Opciones de Entradas Disponibles ===\n");
                for (int i = 0; i < tiposDeEntradas.length; i++) {
                    System.out.println((i + 1) + ". " + tiposDeEntradas[i]);
                    System.out.println("   Estudiante: $" + preciosEstudiante[i]);
                    System.out.println("   General: $" + preciosGeneral[i]);
                    System.out.println(); // Linea en blanco para separar las opciones
                    // Declaramos validadores de entrada
                }

                int indiceSeleccionadoEntrada;
                while (true) {
                    System.out.print("Ingrese el tipo de entrada  (1-4): ");
                    indiceSeleccionadoEntrada = entradaUsuario.nextInt() - 1;
                    entradaUsuario.nextLine();
                    if (indiceSeleccionadoEntrada >= 0 && indiceSeleccionadoEntrada < tiposDeEntradas.length) {
                        break;
                    }
                    System.out.println("Entrada invalida, seleccione un numero entre 1 y 4.");
                }

                // Seleccion de ubicacion del asiento
                System.out.println("\n=== Plano del Teatro ===\n");
                for (int i = 0; i < zonasTeatro.length; i++) {
                    System.out.println((i + 1) + ". " + zonasTeatro[i]);
                }

                int zonaSeleccionada;
                while (true) {
                    System.out.print("Ingrese el numero de la zona deseada (1-3): ");
                    zonaSeleccionada = entradaUsuario.nextInt() - 1;
                    entradaUsuario.nextLine();
                    if (zonaSeleccionada >= 0 && zonaSeleccionada < zonasTeatro.length) {
                        break;
                    }
                    System.out.println("Zona invalida, seleccione un numero entre 1 y 3.");
                }

                // Seleccion de tarifa
                String tipoDeTarifa;
                while (true) {
                    System.out.print("\nIngrese la tarifa deseada (Estudiante o General): ");
                    tipoDeTarifa = entradaUsuario.next().trim();
                    if (tipoDeTarifa.equalsIgnoreCase("Estudiante") || tipoDeTarifa.equalsIgnoreCase("General")) {
                        break;
                    }
                    System.out.println("Tarifa invalida, ingrese 'Estudiante' o 'General'.");
                }

                // Captura de edad con validacion
                int edad;
                while (true) {
                    System.out.print("\nIngrese su edad: ");
                    if (entradaUsuario.hasNextInt()) {
                        edad = entradaUsuario.nextInt();
                        entradaUsuario.nextLine();
                        if (edad >= 0 && edad <= 120) {
                            break;
                        }
                    } else {
                        entradaUsuario.nextLine();
                    }
                    System.out.println("Edad invalida, ingrese un numero valido entre 0 y 120.");
                }

                // Calculo de precio final con descuentos
                double totalAPagar = tipoDeTarifa.equalsIgnoreCase("Estudiante") ?
                        preciosEstudiante[indiceSeleccionadoEntrada] :
                        preciosGeneral[indiceSeleccionadoEntrada];

                double descuento = 0;
                if (tipoDeTarifa.equalsIgnoreCase("Estudiante")) {
                    descuento = totalAPagar * 0.10;
                } else if (edad >= 60) {
                    descuento = totalAPagar * 0.15;
                }

                totalAPagar -= descuento;

                // Resumen de la compra
                System.out.println("\n==============================");
                System.out.println("RESUMEN DE SU COMPRA");
                System.out.println("==============================\n");
                System.out.println("Tipo de Entrada: " + tiposDeEntradas[indiceSeleccionadoEntrada]);
                System.out.println("Ubicacion del asiento: " + zonasTeatro[zonaSeleccionada]);
                System.out.println("Tarifa Seleccionada: " + tipoDeTarifa);
                System.out.println("Precio base: $" + (int) (totalAPagar + descuento));
                System.out.println("Descuento aplicado: $" + (int) descuento);
                System.out.println("Total a Pagar: $" + (int) totalAPagar);
                System.out.println("==============================\n");
                System.out.println("Gracias por su compra, disfrute la funcion\n");
            } else {
                System.out.println("Opcion invalida, seleccione 1 o 2.");
            }
        }

        entradaUsuario.close();
    }
}

