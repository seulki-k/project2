package project2.util;

import java.util.Objects;
import java.util.Scanner;

public class Prompt {
    static Scanner keyboardScanner = new Scanner(System.in);

    public static String input(String format, Object... args) {
        System.out.printf(format + " ", args);
        return keyboardScanner.nextLine();
    }

    public static int inputInt(String format, Objects... args) {
        return Integer.parseInt(input(format, args));
    }
    public static int inputInt2(int a,int b, String format, Objects... args) {
        while(true) {
            int abc = Integer.parseInt(input(format, args));
            if (a > abc || abc > b) {
                System.out.println("제대로 된 값이 아닙니다.");
            }else{
                return abc;
            }
        }
    }

    public static void close() {
        keyboardScanner.close();
    }
}

