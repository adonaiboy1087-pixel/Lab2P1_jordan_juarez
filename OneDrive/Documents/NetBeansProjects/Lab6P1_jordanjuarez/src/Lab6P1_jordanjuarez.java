import java.util.Random;
import java.util.Scanner;

public class Lab6P1_jordanjuarez {




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("Menu de Laboratorio 6");
            System.out.println("1. Carrera de Arreglos");
            System.out.println("2. Cuantos Primos Tienes?");
            System.out.println("3. Rail Fence");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opcion: ");
            
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar salto de linea

            switch (opcion) {
                case 1:
                    ejercicio1(sc);
                    break;
                case 2:
                    ejercicio2(sc);
                    break;
                case 3:
                    ejercicio3(sc);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    // ==========================================
    // EJERCICIO 1: Carrera de Arreglos
    // ==========================================
    public static void ejercicio1(Scanner sc) {
        Random rand = new Random();

        System.out.print("Ingrese la letra para el primer jugador: ");
        char j1 = sc.next().charAt(0);
        while (j1 < 'A' || j1 > 'Z') {
            System.out.println("Debe escoger una letra mayuscula valida. Intente de nuevo.");
            System.out.print("Ingrese la letra para el primer jugador: ");
            j1 = sc.next().charAt(0);
        }

        System.out.print("Ingrese la letra para el segundo jugador: ");
        char j2 = sc.next().charAt(0);
        while (j2 < 'A' || j2 > 'Z' || j2 == j1) {
            if (j2 == j1) {
                System.out.println("Las letras no pueden ser iguales.");
            } else {
                System.out.println("Debe escoger una letra mayuscula valida. Intente de nuevo.");
            }
            System.out.print("Ingrese la letra para el segundo jugador: ");
            j2 = sc.next().charAt(0);
        }

        System.out.print("Ingrese longitud de carrera (15 a 20): ");
        int tam = sc.nextInt();
        while (tam < 15 || tam > 20) {
            System.out.print("Ingrese longitud de carrera (15 a 20): ");
            tam = sc.nextInt();
        }

        char[] arr1 = new char[tam];
        char[] arr2 = new char[tam];

        int pos1 = 0;
        int pos2 = 0;
        int ronda = 1;

        System.out.println("\nComenzando simulacion de carrera....");

        while (pos1 < tam && pos2 < tam) {
            System.out.println("Ronda " + ronda);

            int mov1 = rand.nextInt(5) + 1;
            int mov2 = rand.nextInt(5) + 1;

            pos1 = ActualizarArreglo(arr1, j1, pos1, mov1);
            pos2 = ActualizarArreglo(arr2, j2, pos2, mov2);

            System.out.println("El jugador " + j1 + " se movio " + mov1 + " casillas!");
            System.out.println("El jugador " + j2 + " se movio " + mov2 + " casillas!");

            System.out.println("Resultado actual:");
            
            for (int i = 0; i < tam; i++) {
                if (arr1[i] == '\0') {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[" + arr1[i] + "]");
                }
            }
            System.out.println();

            for (int i = 0; i < tam; i++) {
                if (arr2[i] == '\0') {
                    System.out.print("[ ]");
                } else {
                    System.out.print("[" + arr2[i] + "]");
                }
            }
            System.out.println("\n");

            ronda++;
        }

        if (pos1 >= tam && pos2 >= tam) {
            System.out.println("¡Ha ocurrido un empate!");
        } else if (pos1 >= tam) {
            System.out.println("El jugador " + j1 + " ha ganado!");
        } else {
            System.out.println("El jugador " + j2 + " ha ganado!");
        }
    }

    public static int ActualizarArreglo(char[] arreglo, char jugador, int posActual, int pasos) {
        int nuevaPos = posActual + pasos;
        for (int i = posActual; i < nuevaPos && i < arreglo.length; i++) {
            arreglo[i] = jugador;
        }
        return nuevaPos;
    }

    // ==========================================
    // EJERCICIO 2: ¿Cuántos Primos Tienes?
    // ==========================================
    public static void ejercicio2(Scanner sc) {
        System.out.println("Ejercicio 2 - Cuantos Primos Tienes?");
        
        System.out.print("Ingrese el tamano del arreglo a generar: ");
        int n = sc.nextInt();
        while (n <= 1) {
            System.out.println("Ingrese un tamano mayor a 1");
            System.out.print("Ingrese el tamano del arreglo a generar: ");
            n = sc.nextInt();
        }

        System.out.print("Ingrese el limite inferior: ");
        int min = sc.nextInt();
        System.out.print("Ingrese el limite superior: ");
        int max = sc.nextInt();

        while (max <= min) {
            System.out.println("El limite superior debe ser mayor que el limite inferior.");
            System.out.print("Ingrese el limite inferior: ");
            min = sc.nextInt();
            System.out.print("Ingrese el limite superior: ");
            max = sc.nextInt();
        }

        int[] numeros = genRandArray(n, min, max);
        int[] primosContados = getTotalPrimeCount(numeros);

        for (int i = 0; i < n; i++) {
            System.out.print("[" + numeros[i] + "]");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print("[" + primosContados[i] + "]");
        }
        System.out.println();
    }

    public static int[] genRandArray(int tam, int min, int max) {
        Random r = new Random();
        int[] a = new int[tam];
        for (int i = 0; i < tam; i++) {
            a[i] = r.nextInt(max - min + 1) + min;
        }
        return a;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static int countPrimeFactors(int n) {
        if (n <= 1) return 0;
        int cont = 0;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0 && isPrime(i)) {
                cont++;
            }
        }
        return cont;
    }

    public static int[] getTotalPrimeCount(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = countPrimeFactors(arr[i]);
        }
        return res;
    }

    // ==========================================
    // EJERCICIO 3: Rail Fence
    // ==========================================
    public static void ejercicio3(Scanner sc) {
        System.out.println("Ejercicio 3 - Rail Fence");
        System.out.print("Ingrese la frase que le gustaria cifrar: ");
        String frase = sc.nextLine();

        while (!validarFrase(frase)) {
            System.out.print("Ingrese la frase que le gustaria cifrar: ");
            frase = sc.nextLine();
        }

        String limpia = PrepararFrase(frase);
        System.out.println("Frase preparada: " + limpia);

        int n = limpia.length();

        char[] riel1 = new char[n];
        char[] riel2 = new char[n];
        char[] riel3 = new char[n];

        int riel = 0;
        boolean bajando = true;

        for (int i = 0; i < n; i++) {
            char c = limpia.charAt(i);

            if (riel == 0) {
                riel1[i] = c;
            } else if (riel == 1) {
                riel2[i] = c;
            } else if (riel == 2) {
                riel3[i] = c;
            }

            if (riel == 0) {
                bajando = true;
            } else if (riel == 2) {
                bajando = false;
            }

            if (bajando) {
                riel++;
            } else {
                riel--;
            }
        }

        String cifrado = "";

        for (int i = 0; i < n; i++) {
            if (riel1[i] != '\0') {
                cifrado += riel1[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if (riel2[i] != '\0') {
                cifrado += riel2[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if (riel3[i] != '\0') {
                cifrado += riel3[i];
            }
        }

        System.out.println("Texto cifrado: " + cifrado);
    }

    public static String PrepararFrase(String frase) {
        String res = "";
        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);
            if (c != ' ') {
                res += c;
            }
        }
        return res.toUpperCase();
    }

    public static boolean validarFrase(String f) {
        for (int i = 0; i < f.length(); i++) {
            char c = f.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
                return false;
            }
        }
        return true;
    }
}