import java.util.Observable;
import java.util.Observer;

public class WatcherAccessFile implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        WorkWithFile.writeFile("Event-Accessing File, operation - " + arg.toString());
    }
}