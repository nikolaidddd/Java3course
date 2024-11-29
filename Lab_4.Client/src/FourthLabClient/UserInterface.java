package FourthLabClient;

import java.util.Scanner;

public class UserInterface {
    private final WorkWithServer _server;
    private final Scanner in;

    public UserInterface() {
        in = new Scanner(System.in);

        System.out.println("default settings: \"localhost\" 2345");

        int lengthPacket = 60;
        System.out.print("Enter the address: ");
        String host = in.nextLine();
        System.out.print("Enter the port: ");
        int port = in.nextInt();
        in.nextLine();

        _server = new WorkWithServer(lengthPacket, host, port);
    }


    private int InputFunc() {

        System.out.println("0. Выполнить запрос\n" +
                "1. Завершить работу");
        System.out.print("Enter the command: ");

        String input = in.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void Start() {
        boolean infiCycle = true;


        while (infiCycle) {
            var user_choise = InputFunc();


            switch (user_choise) {
                case 0:
                    String message = "";
                    String operation;
                    String cell;
                    boolean newcellrun = true;

                    Scanner sc = new Scanner(System.in);

                    System.out.println("Номера массивов: 1-int 2-double 3-String\n " +
                            "Номера операций: 1- считать, 2- перезаписать 3- узнать размерность");
                    System.out.println("Введите номер операции  ");
                    operation = sc.nextLine();
                    message = message + operation+":";


                    if (operation.equals("1")){
                        System.out.println("Введите номер массива, затем ячейки, например:1 2 4");
                        cell = sc.nextLine();
                        message+=cell;
                    }

                    else if (operation.equals("2")){

                        System.out.println("Введите номер массива, ячейки и новое значение например:1 2 4 50");
                        String newvar = sc.nextLine();
                        message+=newvar+":";

                            while (newcellrun){
                                System.out.println("Ввести еще одну ячейку?\n1-да 2-нет");
                                int choice2 = sc.nextInt();
                                if(choice2==1) {
                                    System.out.println("Введите еще одну ячейку: ");
                                    sc.nextLine();
                                    String newvar2 = sc.nextLine();
                                    message += newvar2 + ":";
                                }
                                else if(choice2==2){
                                    newcellrun =false;
                                }
                                else {
                                    System.out.println("Неверный ввод");
                                }
                            }
                    }
                    else if (operation.equals("3")){
                        System.out.println("Введите номер массива: ");
                        String arrnum =sc.nextLine();
                        message+=arrnum;
                    }

                    _server.SendAndGet(message);
                    break;

                case 1:
                    infiCycle = false;
                    _server.EndWork();
                    break;

                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
