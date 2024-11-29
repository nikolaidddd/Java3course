package interfaces;

import java.util.ArrayList;
import java.util.Collections;

public class lab2Class implements lab2IF,IHandler{

    public void fillarr(String[] args){
        for (String x : args) {
            al1.add(Integer.parseInt(x));
            Collections.sort(al1, Collections.reverseOrder());
        }
        System.out.println("Отсортированный массив:" + al1);

    }

    public void Handle() {

        try {
            if (s < al1.size()){
                System.out.println("В массиве больше " + s +" элементов.");
                }
            else { throw new Exception1();
            }
        }catch (Exception1 e){
            System.out.println(e.toString());
        }

        try {
            if (!((al1.toString()).contains(Character.toString(s2)))){
                throw new Exception2();
            }

            System.out.println("В списке есть символ "+ Character.toString(s2));
        }catch (Exception2 e){
            System.out.println(e.toString());
        }

        try {
            if (al1.toString().contains(Character.toString(s3))){
                throw new Exception3();
            }
            System.out.println("В списке нет символа "+ s3);
        }catch (Exception3 e){
            System.out.println(e.toString());
        }
    }
}
