package Recursividad;
import java.util.Scanner;

public class DivisionConResta {

    private static Scanner scanner = new Scanner(System.in);

    //funcion de manera recursivo
    private static int division(int dividendo, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("El divisor no puede ser cero.");
        }

        if (dividendo < divisor) {
            return 0;
        }

        return 1 + division(dividendo - divisor, divisor);
    }
    //funcion iterativo
    private static int division(int dividendo, int divisor, boolean iterativo) {
        if (divisor == 0) {
            throw new IllegalArgumentException("El divisor no puede ser cero.");
        }

        if (iterativo) {
            int cociente = 0;
            while (dividendo >= divisor) {
                dividendo -= divisor;
                cociente++;
            }
            return cociente;
        } else {
            return division(dividendo, divisor);
        }
    }

    // ejecucion
    public static void main(String[] args) {
        System.out.println("Ingrese el dividendo");
        int dividendo = scanner.nextInt();
        System.out.println("Ingrese el divisor");
        int divisor = scanner.nextInt();

        int resultadoRecursivo = division(dividendo, divisor);
        int resultadoIterativo = division(dividendo, divisor, true);

        System.out.println("Resultado Recursivo: " + resultadoRecursivo);
        System.out.println("Resultado Iterativo: " + resultadoIterativo);
    }
}
