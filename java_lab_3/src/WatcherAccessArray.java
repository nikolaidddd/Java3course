import java.util.Observable;
import java.util.Observer;

public class WatcherAccessArray implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        WorkWithFile.writeFile("Event-Accessing Array: " + arg.toString());
    }
}
