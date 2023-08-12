import java.util.Scanner;

public class PrimerEjercicio {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un numero");
        int num1 = scanner.nextInt();
        System.out.println("Ingrese el segundo");
        int num2 = scanner.nextInt();
        System.out.println("Ingrese el tercer numero");
        int num3 = scanner.nextInt();

        if (num1 > num2 && num2 > num3){
            System.out.println("Los numeros de mayor a menor son:" + num1 + " " + num2 + " " + num3);
        } else if (num1 > num3 && num3 > num2) {
            System.out.println("Los numeros de mayor a menor son:" + num1 + " " + num3 + " " + num2);
        } else if (num2 > num1 && num1 > num3) {
            System.out.println("Los numeros de mayor a menor son:" + num2 + " " + num1 + " " + num3);
        } else if (num2 > num3 && num3 > num1) {
            System.out.println("Los numeros de mayor a menor son:" + num2 + " " + num3 + " " + num1);
        } else if (num3 > num1 && num1 > num2) {
            System.out.println("Los numeros de mayor a menor son:" + num3 + " " + num1 + " " + num2);
        } else if (num3 > num2 && num2 > num1) {
            System.out.println("Los numeros de mayor a menor son:" + num3 + " " + num2 + " " + num1);
        }
    }
}
