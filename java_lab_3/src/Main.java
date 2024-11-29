import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String messageGetLogPath = "\nВведите путь к файлу журнала";

        Processor processorBeWatched = new Processor();
        WatcherAccessArray watcherProcessor = new WatcherAccessArray();
        processorBeWatched.addObserver(watcherProcessor);

        WorkWithConsole appCycleBeWatched = new WorkWithConsole(processorBeWatched);
        WatcherAccessConsole watcherConsole = new WatcherAccessConsole();
        appCycleBeWatched.addObserver(watcherConsole);


        System.out.println(messageGetLogPath);
        var _logPath = Reader.readFilePath();
        if(_logPath == null){
            _logPath = "log.txt";
        }
        WorkWithFile.SetLogPath(_logPath);
        appCycleBeWatched.notifyWithConsole(messageGetLogPath);
        appCycleBeWatched.appCycle();
    }
}