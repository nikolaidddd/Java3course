import java.util.Observable;
import java.util.Observer;

public class WatcherAccessConsole implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        WorkWithFile.writeFile("Event-Accessing Console Output: " + arg.toString());
    }
}