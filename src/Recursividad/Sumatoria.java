package Recursividad;
import java.util.Scanner;

public class Sumatoria {
    private static Scanner scanner = new Scanner(System.in);
    public static int sumatoria(int num) {
        // Caso base: cuando n es 0, la sumatoria es 0
        if (num == 0) {
            return 0;
        }

        // Caso recursivo: suma n y el resultado de la sumatoria de (n-1)
        return num + sumatoria(num - 1);
    }

    public static void main(String[] args) {
        System.out.println("Ingrese un numero");
        int num = scanner.nextInt();
        int resultado = sumatoria(num);
        System.out.println("La sumatoria de " + num + " es " + resultado);
    }
}