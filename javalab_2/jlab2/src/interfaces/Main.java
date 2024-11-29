package interfaces;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception3, Exception1, Exception2 {
        System.out.println("1. В массиве число элементов меньше указанного\n" +
                "2. В строке отсутствует какой-то символ\n" +
                "6. Строка содержит некоторый символ\n");

        lab2Class test = new lab2Class();
        test.fillarr(args);
        Scanner sc = new Scanner(System.in);

        test.Handle();

    }
}
