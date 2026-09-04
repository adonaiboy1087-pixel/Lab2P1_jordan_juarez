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

public class lab7p1_jordanjuarez {

    private static final int ALTO_TABLERO = 10;
    private static final int ANCHO_TABLERO = 15;

    public static void main(String[] args) {
        Scanner entradaConsola = new Scanner(System.in);
        int seleccionMenu;

        do {
            System.out.println("--Menu de opciones--");
            System.out.println("0. Salir");
            System.out.println("1. Pacman");
            System.out.print("Ingrese su opcion: ");
            seleccionMenu = entradaConsola.nextInt();

            if (seleccionMenu == 1) {
                comenzarSesionJuego(entradaConsola);
            } else if (seleccionMenu != 0) {
                System.out.println("Opcion invalida. Intente de nuevo.\n");
            }
        } while (seleccionMenu != 0);

        System.out.println("¡Gracias por jugar!");
        entradaConsola.close();
    }

    public static void comenzarSesionJuego(Scanner entradaConsola) {
        char[][] grillaJuego = new char[ALTO_TABLERO][ANCHO_TABLERO];
        char[][] grillaAuxiliar = new char[ALTO_TABLERO][ANCHO_TABLERO];
        int puntajeTotal = 0;
        boolean estadoActivo = true;

        System.out.println("\nBIENVENIDO A PACMAN:");

        prepararGrillaYHeroe(grillaJuego, grillaAuxiliar);
        ubicarObstaculosYFantasmas(grillaJuego, grillaAuxiliar);
        renderizarTablero(grillaJuego, puntajeTotal);

        while (estadoActivo) {
            System.out.println("ESCOGE TU JUGADA!");
            System.out.print("W(ARRIBA), S(ABAJO), A(IZQUIERDA), D(DERECHA) : ");
            char entradaTeclado = entradaConsola.next().charAt(0);
            entradaTeclado = Character.toLowerCase(entradaTeclado);

            if (entradaTeclado != 'w' && entradaTeclado != 's' && entradaTeclado != 'a' && entradaTeclado != 'd') {
                System.out.println("Movimiento no valido. Intente de nuevo.\n");
                continue;
            }

            int[] datosMovimiento = procesarDireccionPacman(grillaJuego, grillaAuxiliar, puntajeTotal, entradaTeclado);
            puntajeTotal = datosMovimiento[0];
            
            if (datosMovimiento[1] == 1) {
                renderizarTableroConAviso(grillaJuego, puntajeTotal, "Fuiste atrapado, has perdido!");
                estadoActivo = false;
                break;
            }

            if (puntajeTotal >= 1000) {
                renderizarTableroConAviso(grillaJuego, puntajeTotal, "Llegaste a 1000 puntos, has ganado!");
                estadoActivo = false;
                break;
            }

            boolean colisionConFantasma = moverEnemigosAleatoriamente(grillaJuego, grillaAuxiliar);
            if (colisionConFantasma) {
                renderizarTableroConAviso(grillaJuego, puntajeTotal, "Fuiste atrapado, has perdido!");
                estadoActivo = false;
                break;
            }

            renderizarTablero(grillaJuego, puntajeTotal);
        }
        System.out.println();
    }

    public static void prepararGrillaYHeroe(char[][] grillaJuego, char[][] grillaAuxiliar) {
        Random generadorAzar = new Random();
        for (int i = 0; i < ALTO_TABLERO; i++) {
            for (int j = 0; j < ANCHO_TABLERO; j++) {
                grillaJuego[i][j] = '*';
                grillaAuxiliar[i][j] = '*';
            }
        }

        int filaHeroe, colHeroe;
        do {
            filaHeroe = generadorAzar.nextInt(ALTO_TABLERO);
            colHeroe = generadorAzar.nextInt(ANCHO_TABLERO);
        } while (grillaJuego[filaHeroe][colHeroe] != '*');

        grillaJuego[filaHeroe][colHeroe] = 'C';
        grillaAuxiliar[filaHeroe][colHeroe] = '*';
    }

    public static void ubicarObstaculosYFantasmas(char[][] grillaJuego, char[][] grillaAuxiliar) {
        Random generadorAzar = new Random();

        int contadorObstaculos = 0;
        while (contadorObstaculos < 8) {
            int x = generadorAzar.nextInt(ALTO_TABLERO);
            int y = generadorAzar.nextInt(ANCHO_TABLERO);
            if (grillaJuego[x][y] == '*') {
                grillaJuego[x][y] = 'O';
                grillaAuxiliar[x][y] = 'O';
                contadorObstaculos++;
            }
        }

        while (true) {
            int x = generadorAzar.nextInt(ALTO_TABLERO);
            int y = generadorAzar.nextInt(ANCHO_TABLERO);
            if (grillaJuego[x][y] == '*') {
                grillaJuego[x][y] = 'B';
                grillaAuxiliar[x][y] = 'B';
                break;
            }
        }

        char[] listaFantasmas = {'R', 'M', 'G'};
        for (char fantasmaActual : listaFantasmas) {
            while (true) {
                int x = generadorAzar.nextInt(ALTO_TABLERO);
                int y = generadorAzar.nextInt(ANCHO_TABLERO);
                if (grillaJuego[x][y] == '*') {
                    grillaJuego[x][y] = fantasmaActual;
                    break;
                }
            }
        }
    }

    public static void renderizarTablero(char[][] grillaJuego, int puntajeTotal) {
        System.out.println("--------------------------------");
        System.out.println("\nPUNTOS || " + puntajeTotal + "\n");
        for (int i = 0; i < ALTO_TABLERO; i++) {
            for (int j = 0; j < ANCHO_TABLERO; j++) {
                System.out.print(grillaJuego[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void renderizarTableroConAviso(char[][] grillaJuego, int puntajeTotal, String textoAviso) {
        System.out.println("--------------------------------");
        System.out.println("\nPUNTOS || " + puntajeTotal + "\n");
        for (int i = 0; i < ALTO_TABLERO; i++) {
            for (int j = 0; j < ANCHO_TABLERO; j++) {
                System.out.print(grillaJuego[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n" + textoAviso);
    }

    public static int[] buscarCoordenadaDe(char[][] grillaJuego, char objetivoBusqueda) {
        for (int i = 0; i < ALTO_TABLERO; i++) {
            for (int j = 0; j < ANCHO_TABLERO; j++) {
                if (grillaJuego[i][j] == objetivoBusqueda) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static int[] procesarDireccionPacman(char[][] grillaJuego, char[][] grillaAuxiliar, int puntajeTotal, char entradaTeclado) {
        int[] ubicacionActual = buscarCoordenadaDe(grillaJuego, 'C');
        int filaActual = ubicacionActual[0];
        int colActual = ubicacionActual[1];

        int filaDestino = filaActual;
        int colDestino = colActual;

        if (entradaTeclado == 'w') {
            filaDestino = (filaActual - 1 + ALTO_TABLERO) % ALTO_TABLERO;
        } else if (entradaTeclado == 's') {
            filaDestino = (filaActual + 1) % ALTO_TABLERO;
        } else if (entradaTeclado == 'a') {
            colDestino = (colActual - 1 + ANCHO_TABLERO) % ANCHO_TABLERO;
        } else if (entradaTeclado == 'd') {
            colDestino = (colActual + 1) % ANCHO_TABLERO;
        }

        char contenidoCasilla = grillaJuego[filaDestino][colDestino];

        if (contenidoCasilla == 'O') {
            System.out.println("--------------------------------");
            System.out.println("Obstaculo!\n");
            return new int[]{puntajeTotal, 0};
        }

        if (contenidoCasilla == 'R' || contenidoCasilla == 'M' || contenidoCasilla == 'G') {
            grillaJuego[filaActual][colActual] = grillaAuxiliar[filaActual][colActual];
            grillaJuego[filaDestino][colDestino] = 'X';
            return new int[]{puntajeTotal, 1};
        }

        grillaJuego[filaActual][colActual] = grillaAuxiliar[filaActual][colActual];

        if (contenidoCasilla == '*') {
            puntajeTotal += 50;
            grillaAuxiliar[filaDestino][colDestino] = '*';
        } else if (contenidoCasilla == 'B') {
            puntajeTotal += 150;
            grillaAuxiliar[filaDestino][colDestino] = '*';
        }

        grillaJuego[filaDestino][colDestino] = 'C';
        return new int[]{puntajeTotal, 0};
    }

    public static boolean moverEnemigosAleatoriamente(char[][] grillaJuego, char[][] grillaAuxiliar) {
        char[] listaFantasmas = {'R', 'M', 'G'};
        Random generadorAzar = new Random();

        for (char fantasmaActual : listaFantasmas) {
            int[] posicionFantasma = buscarCoordenadaDe(grillaJuego, fantasmaActual);
            if (posicionFantasma[0] == -1) continue;

            int filaActual = posicionFantasma[0];
            int colActual = posicionFantasma[1];

            int sentidoMovimiento = generadorAzar.nextInt(4);
            int filaDestino = filaActual;
            int colDestino = colActual;

            if (sentidoMovimiento == 0) {
                filaDestino = (filaActual - 1 + ALTO_TABLERO) % ALTO_TABLERO;
            } else if (sentidoMovimiento == 1) {
                filaDestino = (filaActual + 1) % ALTO_TABLERO;
            } else if (sentidoMovimiento == 2) {
                colDestino = (colActual - 1 + ANCHO_TABLERO) % ANCHO_TABLERO;
            } else if (sentidoMovimiento == 3) {
                colDestino = (colActual + 1) % ANCHO_TABLERO;
            }

            char casillaObjetivo = grillaJuego[filaDestino][colDestino];

            if (casillaObjetivo == 'O' || casillaObjetivo == 'R' || casillaObjetivo == 'M' || casillaObjetivo == 'G') {
                continue;
            }

            if (casillaObjetivo == 'C') {
                grillaJuego[filaActual][colActual] = grillaAuxiliar[filaActual][colActual];
                grillaJuego[filaDestino][colDestino] = fantasmaActual;
                return true;
            }

            grillaJuego[filaActual][colActual] = grillaAuxiliar[filaActual][colActual];
            grillaJuego[filaDestino][colDestino] = fantasmaActual;
        }

        return false;
    }
}