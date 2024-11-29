package FourthLabClient;

import ThirdLab.Watcher;
import ThirdLab.WorkWithConsole;
import ThirdLab.WorkWithFile;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Observable;
import java.util.Scanner;

public class WorkWithServer extends Observable {
    private final int _lengthPacket;
    private final int _port;
    private final WorkWithConsole _console;
    private final WorkWithFile _filePrinter;
    private InetAddress _addr;
    private DatagramSocket _socket;
    Scanner sc = new Scanner(System.in);


    public WorkWithServer() {
        this(60, "localhost", 2345);
    }

    public WorkWithServer(int lengthPacket, String host, int port) {


        _lengthPacket = lengthPacket;
        _port = port;

        _console = new WorkWithConsole();
        _console.OutputInConsole("Введите адрес журнала клиента: ");
        String logpath = sc.nextLine();

        _filePrinter = new WorkWithFile(logpath);
        Watcher watcherWhoWriteInFile = new Watcher(_filePrinter);
        _console.addObserver(watcherWhoWriteInFile);

        try {
            _addr = InetAddress.getByName(host);
            _socket = new DatagramSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Отправка сообщения без ожидания получения
     * Метод получает строковое сообщение, которое нужно отправить серверу
     *
     * @param msg строкоовое сообщение для сервера
     * @return 0 - всё прошло успешно, -1 - при отправке возникла ошибка, -2 - Превышен размер отправляемого сообщения
     */
    public int SendMessage(String msg) {
        byte[] data = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, _addr, _port);

        if (data.length < _lengthPacket) {
            try {
                _socket.send(packet);
                _console.OutputInConsole("Message sent...");
                return 0;
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            }
        } else {
            _console.OutputInConsole("The size of the message being sent has been exceeded");
            return -2;
        }
    }

    /**
     * Метод получения сообщений от Сервера
     *
     * @return вовзращает в виде строки полученное сообщение
     */
    private String RecieveMessage() {
        String result = "";

        try {
            byte[] data2;
            data2 = new byte[_lengthPacket];
            DatagramPacket packet = new DatagramPacket(data2, data2.length);
            _socket.receive(packet);
            result = (new String(packet.getData())).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }

        _console.OutputInConsole("Received from the server: " + result);
        return result;
    }

    /**
     * Метод для отправки сообщения и последующего ожидания получения
     *
     * @param message - отправляемое сообщение
     * @return - ответ от сервера
     */
    public String SendAndGet(String message) {
        var check = SendMessage(message);
        String result = "-1";

        if (check == 0)
            result = AwaitMessage();

        return result;
    }

    /**
     * Метод ожидания получения сообщения от сервера
     *
     * @return - ответ сервера
     */
    public String AwaitMessage() {
        return RecieveMessage();
    }

    public void EndWork() {
        _socket.close();
    }

}
