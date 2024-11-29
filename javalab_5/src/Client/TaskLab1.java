
package Client;

import Compute.Task;

import java.io.Serializable;

public class TaskLab1 implements Task<String>, Serializable {
    private String[] _args;


    public TaskLab1(String[] args) {
        this._args = args;
    }


    public String execute() {
        return computeTask(_args);
    }

    public static String computeTask(String[] args) {
        int sumOddNumbers = 0; //переменная-счетчик для нечетных чисел
        int sumEvenNumbers = 0; //переменная-счетчик для нечетных чисел

        for (String x : args) {//Вывод всех заданных параметров командной строки
 /* преобразвание строки, хранящейся в переменной "х" в целое число
 и проверка этого числа на чётность*/
            int temp = Integer.parseInt(x);
            if (temp % 2 == 0) {
                sumEvenNumbers += temp;
            } else {
                sumOddNumbers += temp;
            }
        }
        String result = "\nSum of odd numbers: " + sumOddNumbers + "\nSum of even numbers: " + sumEvenNumbers + "\n";
        return result;
    }
}