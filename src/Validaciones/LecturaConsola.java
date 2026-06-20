package Validaciones;

import java.util.Scanner;

public class LecturaConsola {
    //Instanciamos el scanner
    private static Scanner sc = new Scanner(System.in);

    //Metodos auxiliares (nos ayudan con las validaciones de entrada)
    public static int ingresarEntero(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            try {
                //Utilizamos .trim() para recortar los espacios en blanco que sobran al principio y al final de un texto
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Ingrese un número entero válido.");
            }
        }
    }

    public static String ingresarOpcionNoVacia(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            //Utilizamos .trim() para recortar los espacios en blanco que sobran al principio y al final de un texto
            String entrada = sc.nextLine().trim();
            if (!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("[ERROR] No puede estar vacio.");
        }
    }
}
