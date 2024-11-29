import java.util.Arrays;
import java.util.Observable;

public class WorkWithConsole extends Observable{

    private final Processor _processor;
    public FileNotify fileNotify = new FileNotify();

    public WorkWithConsole(Processor processor){

        _processor = processor;

    }

    public void notifyWithConsole(Object obj){
        fileNotify.notifyWithFile("writing");
        setChanged();
        notifyObservers(obj);

    }

    private String showMenu(){
        String menu = "\n" +
                "1. Ввести массив чисел для расчета в консоль.\n" +
                "2. Ввести массив чисел из файла.\n" +
                "3. Завершить работу.\n";
        System.out.println(menu);
        return menu;
    }

    public void appCycle(){
        String messageArrArgs = "\nВведите строку аргументов";
        String messageGetPath = "\nВведите путь к файлу с данными";

        boolean run = true;
        String choose;
        while(run){
            var menu = showMenu();
            choose = Reader.readConsoleString();
            notifyWithConsole(menu);

            switch (choose) {
                case "1" -> {
                    System.out.println(messageArrArgs);
                    String[] consoleInpStrArr = Reader.readConsoleStringArgs();
                    notifyWithConsole( messageArrArgs);
                    int[] resArr1 = _processor.SortingArr(consoleInpStrArr);
                    String resMessage1 = "Отсортированный массив: "+ Arrays.toString(resArr1);
                    System.out.println(resMessage1);
                    notifyWithConsole(resMessage1);


                }
                case "2" -> {
                    System.out.print(messageGetPath);
                    String inFilePath = Reader.readFilePath();
                    notifyWithConsole(messageGetPath);
                    String[] fileInpStrArr = Reader.readFileStringArgs(inFilePath);
                    int[] resArr2 = _processor.SortingArr(fileInpStrArr);
                    String resMessage2 = "Отсортированный массив: "+ Arrays.toString(resArr2);
                    System.out.println(resMessage2);
                    notifyWithConsole(resMessage2);
                }
                case "3" -> run = false;
                default -> {
                }
            }
        }
    }

}
