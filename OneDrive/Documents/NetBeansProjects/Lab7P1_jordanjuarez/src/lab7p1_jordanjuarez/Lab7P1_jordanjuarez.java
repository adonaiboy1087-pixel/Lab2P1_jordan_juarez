/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab7p1_jordanjuarez;

/**
 *
 * @author adona
 */
import java.util.Random;
import java.util.Scanner;
public class Lab7P1_jordanjuarez {

  


    // Dimensiones del tablero
    private static final int FILAS = 10;
    private static final int COLUMNAS = 15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("--Menu de opciones--");
            System.out.println("0. Salir");
            System.out.println("1. Pacman");
            System.out.print("Ingrese su opcion: ");
            opcion = scanner.nextInt();

            if (opcion == 1) {
                ejecutarJuego(scanner);
            } else if (opcion != 0) {
                System.out.println("Opcion invalida. Intente de nuevo.\n");
            }
        } while (opcion != 0);

        System.out.println("¡Gracias por jugar!");
        scanner.close();
    }

    // Método principal encargado de inicializar y ejecutar el juego
    public static void ejecutarJuego(Scanner scanner) {
        char[][] tablero = new char[FILAS][COLUMNAS];
        char[][] tableroOriginal = new char[FILAS][COLUMNAS];
        int puntaje = 0;
        boolean juegoActivo = true;

   