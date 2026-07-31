/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab1p1_jordanjuarez;

import java.util.Scanner;

public class Lab1P1_jordanjuarez {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner lea = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. ¿Es un numero compuesto?");
            System.out.println("2. Aproximacion de Euler");
            System.out.println("3. Numero de Armstrong");
            System.out.println("4. Salir");
            System.out.print("Elija una opcion: ");
            opcion = lea.nextInt();

            if (opcion == 1) {

                // EJERCICIO 1
                System.out.println("\nEjercicio 1 - Numero compuesto");

                int numero;
                int divisor = 2;
                boolean compuesto = false;

                do {
                    System.out.print("Ingrese un numero mayor que 1: ");
                    numero = lea.nextInt();

                    if (numero <= 1) {
                        System.out.println("Numero invalido.");
                    }

                } while (numero <= 1);

                while (divisor < numero && !compuesto) {

                    if (numero % divisor == 0) {
                        compuesto = true;
                    }

                    divisor++;
                }

                if (compuesto) {
                    System.out.println(numero + " es un numero compuesto.");
                } else {
                    System.out.println(numero + " no es un numero compuesto.");
                }

            } else if (opcion == 2) {

                // EJERCICIO 2
                System.out.println("\nEjercicio 2 - Aproximacion de Euler");

                int nivel;

                do {
                    System.out.print("Ingrese el nivel de aproximacion: ");
                    nivel = lea.nextInt();

                    if (nivel < 1) {
                        System.out.println("Debe ingresar un numero mayor o igual que 1.");
                    }

                } while (nivel < 1);

                double sumaEuler = 1.0;
                int contadorEuler = 1;

                while (contadorEuler <= nivel) {

                    double factorial = 1.0;
                    int i = 1;

                    while (i <= contadorEuler) {
                        factorial = factorial * i;
                        i++;
                    }

                    sumaEuler = sumaEuler + (1.0 / factorial);
                    contadorEuler++;
                }

                System.out.println(
                        "La aproximacion de Euler con un nivel de "
                        + nivel + " es: " + sumaEuler
                );

            } else if (opcion == 3) {

                // EJERCICIO 3
                System.out.println("\nEjercicio 3 - Numero de Armstrong");

                int numeroArmstrong;

                do {
                    System.out.print("Ingrese un numero positivo: ");
                    numeroArmstrong = lea.nextInt();

                    if (numeroArmstrong < 0) {
                        System.out.println("Numero invalido.");
                    }

                } while (numeroArmstrong < 0);

                int copia = numeroArmstrong;
                int digitos = 0;

                // Contar cuántos dígitos tiene el número
                if (numeroArmstrong == 0) {
                    digitos = 1;
                } else {
                    while (copia > 0) {
                        digitos++;
                        copia = copia / 10;
                    }
                }

                copia = numeroArmstrong;
                int sumaArmstrong = 0;

                // Caso especial para el número 0
                if (numeroArmstrong == 0) {
                    sumaArmstrong = 0;
                }

                // Recorrer cada dígito
                while (copia > 0) {

                    int digito = copia % 10;
                    int potencia = 1;
                    int contadorPotencia = 1;

                    // Elevar el dígito a la cantidad de dígitos
                    while (contadorPotencia <= digitos) {
                        potencia = potencia * digito;
                        contadorPotencia++;
                    }

                    sumaArmstrong = sumaArmstrong + potencia;
                    copia = copia / 10;
                }

                if (sumaArmstrong == numeroArmstrong) {
                    System.out.println(
                            numeroArmstrong + " es un numero de Armstrong."
                    );
                } else {
                    System.out.println(
                            numeroArmstrong + " no es un numero de Armstrong."
                    );
                }

            } else if (opcion == 4) {

                System.out.println("Programa finalizado.");

            } else {

                System.out.println("Opcion invalida.");
            }

        } while (opcion != 4);

        lea.close();
    }
}