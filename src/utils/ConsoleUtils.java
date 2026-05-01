package utils;

import java.util.Scanner;

public class ConsoleUtils {

    private static Scanner sc = new Scanner(System.in);

    public static void pause() {
        System.out.print("\nAppuyez sur Entrée pour continuer...\n");
        sc.nextLine();
        clearConsole();
    }
    
    public static void clearConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}