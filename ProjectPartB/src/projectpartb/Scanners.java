package projectpartb;

import java.time.LocalDate;
import java.time.format.*;
import java.util.*;

public abstract class Scanners {
    
    public static double scanPositiveDouble(Scanner scanner, String text) {
        boolean valid;
        String b;
        double a = -1.0;
        do{
            do {
                System.out.println("Please tell me " + text);
                try {
                    b = scanner.next();
                    a = Double.parseDouble(b);
                    valid = true;
                } catch (NumberFormatException e1) {
                    System.out.println("***INVALID INPUT***");
                    valid = false;
                }
            } while (!valid);
        }while(a<0);
        return a;
    }
    
    public static String scanString(Scanner scanner, String text) {
        System.out.println("Please tell me " + text);
        return scanner.next();
    }

    public static int scanPositiveInt(Scanner scanner, String text) {
        boolean valid;
        String b;
        int a = -1;
        do{
            do {
                System.out.println("Please tell me " + text);
                try {
                    b = scanner.next();
                    a = Integer.parseInt(b);
                    valid = true;
                } catch (NumberFormatException e1) {
                    System.out.println("***INVALID INPUT***");
                    valid = false;
                }
            } while (!valid);
        }while(a<0);
        return a;
    }

    public static LocalDate scanDate(Scanner scanner, String text) {
        boolean isFalse = true;
        LocalDate d = null;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            System.out.println("Please enter " + text + " (dd/MM/yyyy)");
            try {
                String dateFromInput = scanner.next();
                d = LocalDate.parse(dateFromInput, df);
                isFalse = false;
            } catch (DateTimeParseException e) {
                System.out.println("***INVALID DATE***");
            }
        } while (isFalse);
        return d;
    }
}
