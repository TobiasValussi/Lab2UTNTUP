package Recursividad;
import java.util.Scanner;

class FactorialConSobrecarga {
    // Método para calcular el factorial de un número de manera recursiva
    public static long calcularFactorialRecursivo(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * calcularFactorialRecursivo(n - 1);
        }
    }

    // Método para calcular el factorial de un número de manera iterativa
    public static long calcularFactorialIterativo(long n) {
        long factorial = 1;
        for (long i = 1; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}

class FactorialProgram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa un número entero para calcular su factorial: ");
        int numero = scanner.nextInt();

        scanner.close();

        long factorialRecursivo = FactorialConSobrecarga.calcularFactorialRecursivo(numero);
        long factorialIterativo = FactorialConSobrecarga.calcularFactorialIterativo(numero);

        System.out.println("Factorial de " + numero + " (recursivo) = " + factorialRecursivo);
        System.out.println("Factorial de " + numero + " (iterativo) = " + factorialIterativo);
    }
}

