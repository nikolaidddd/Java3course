import ThirdLab.MyMath;
import ThirdLab.Watcher;
import ThirdLab.WorkWithConsole;
import ThirdLab.WorkWithFile;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final int LENGTH_PACKET = 30;
    public static final String HOST = "localhost";
    public static int PORT;
    public static byte[] answer;
    private static WorkWithConsole _console;
    private static WorkWithFile _filePrinter;

    private static int[][] intArray;
    private static double[][] doubleArray;
    private static String[][] stringArray;
    public static void printIntArray(int[][] array) {
        for (int[] row : array) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
    public static void printStringArray(String[][] array) {
        for (String[] row : array) {
            for (String element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
    public static void printDoubleArray(double[][] array) {
        for (double[] row : array) {
            for (double element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        DatagramSocket servSocket = null;
        DatagramPacket datagram;
        InetAddress clientAddr;

        intArray = new int[5][5];
        doubleArray = new double[5][5];
        stringArray = new String[5][5];

        _console = new WorkWithConsole();
        var path = args[0];
        _console.OutputInConsole("Enter the PORT:");

        PORT = Integer.parseInt(_console.Input());

        _filePrinter = new WorkWithFile(path);
        Watcher watcherWhoWriteInFile = new Watcher(_filePrinter);
        _console.addObserver(watcherWhoWriteInFile);

        int clientPort;
        byte[] data;
        try {
            servSocket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            System.err.println("Не удаётся открыть сокет : " + e.toString());
        }


        String result = "";
        String defaultstr = "4311";
        int defaultint =4311;
        double defaultdouble = 4311;

        while (true) {
            try {
                // ------------------------------------------------------------
                //очень... долгий цикл
                //приём данных от клиента
                // ------------------------------------------------------------

                data = new byte[LENGTH_PACKET];
                datagram = new DatagramPacket(data, data.length);
                servSocket.receive(datagram);

                String RecMessage = new String(datagram.getData(),0,datagram.getLength());

                _console.OutputInConsole("Get from client: " + RecMessage + " address - " + datagram.getPort());
                String[] splitmes = RecMessage.split(":");
                String operation = splitmes[0];
                String[] cell = splitmes[1].split(" ");


                if (operation.equals("1")){ //вывести ячейку

                    int[] numbers = new int[cell.length];

                    for (int i = 0; i < cell.length; i++) {
                        numbers[i] = Integer.parseInt(cell[i]);
                    }
                    if(cell[0].equals("1")){
                        result = String.valueOf(intArray[numbers[1]][numbers[2]]);
                    } else if (cell[0].equals("2")) {
                        result = String.valueOf(doubleArray[numbers[1]][numbers[2]]);

                    } else if (cell[0].equals("3")) {
                        result = String.valueOf(stringArray[numbers[1]][numbers[2]]);

                    }

                }
                else if(operation.equals("2")){ // перезаписать
                    String[] groups = RecMessage.split(":");
                    int[][] numbers = new int[groups.length][];
                    // Создание списка для групп с строками
                    List<String[]> stringGroups = new ArrayList<>();

                    for (int i = 0; i < groups.length; i++) {
                        String[] group = groups[i].trim().split(" ");
                        numbers[i] = new int[group.length];
                        for (int j = 0; j < group.length; j++) {
                            try {
                                numbers[i][j] = Integer.parseInt(group[j]);
                            } catch (NumberFormatException e) {
                                // Обработка недопустимых значений (строка)
                                numbers[i][j] = 0; // Или другое значение по умолчанию
                                stringGroups.add(group); // добавляем группу со строкой в arraylist

                            }
                        }
                    }
                    if(numbers.length>2){
                        for (int i = 1;i< numbers.length;i++){
                            if(numbers[i][0] == 1){
                                intArray[(numbers[i][1])][(numbers[i][2])] = defaultint;
                                printIntArray(intArray);
                            }
                            else if(numbers[i][0] == 2){
                                doubleArray[(numbers[i][1])][(numbers[i][2])] = defaultdouble;
                                printDoubleArray(doubleArray);
                            }
                            else if(numbers[i][0] == 3){
                                stringArray[(numbers[i][1])][(numbers[i][2])] = defaultstr;
                                printStringArray(stringArray);
                            }

                        }
                    }
                    else {
                        String[][] groupsArray = stringGroups.toArray(new String[0][]);
                        int ind=0;

                            if(numbers[1][0] == 1){
                                intArray[(numbers[1][1])][(numbers[1][2])] = numbers[1][3];
                                printIntArray(intArray);
                            }
                            else if(numbers[1][0] == 2){
                                doubleArray[(numbers[1][1])][(numbers[1][2])] = numbers[1][3];
                                printDoubleArray(doubleArray);
                            }
                            else if(numbers[1][0] == 3){
                                stringArray[(numbers[1][1])][(numbers[1][2])] = String.valueOf(groupsArray[ind][3]);
                                ind++;
                                printStringArray(stringArray);
                            }
                    }
                } else if (operation.equals("3")) {
                    if (splitmes[1].equals("1")){
                        int rows = intArray.length; // Получение количества строк
                        int columns = intArray[0].length; // Получение длины первой строки
                        result = ("rows: "+rows+"columns: "+columns);
                    }
                    if (splitmes[1].equals("2")){
                        int rows = doubleArray.length; // Получение количества строк
                        int columns = doubleArray[0].length; // Получение длины первой строки
                        result = ("rows: "+rows+"columns: "+columns);
                    }
                    if (splitmes[1].equals("1")){
                        int rows = stringArray.length; // Получение количества строк
                        int columns = stringArray[0].length; // Получение длины первой строки
                        result = ("rows: "+rows+"columns: "+columns);
                    }

                }


                _console.OutputInConsole("Computing for " + datagram.getPort() + " Operations compeleted ");
                answer = result.getBytes();
                // ------------------------------------------------------------
                //отправка данных клиенту
                //адрес и порт можно вычислить из предыдущей сессии приёма, через
                //объект класс DatagramPacket - datagram
                // ------------------------------------------------------------
                clientAddr = datagram.getAddress();
                clientPort = datagram.getPort();

                // ------------------------------------------------------------
                //приписывание к полученному сообщению текста " приписка от сервера" и отправка
                //результата обратно клиенту
                // ------------------------------------------------------------
                datagram.setData(answer);
                data = answer;
                datagram = new DatagramPacket(data, data.length, clientAddr, clientPort);
                servSocket.send(datagram);
            } catch (IOException e) {
                System.err.println("io исключение : " + e.toString());
            }
        }
    }
}