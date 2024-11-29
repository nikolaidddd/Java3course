import java.util.Observable;

public class FileNotify extends Observable {

    public FileNotify() {

        WatcherAccessFile watcherFile = new WatcherAccessFile();
        this.addObserver(watcherFile);
    }


    public void notifyWithFile(Object obj){
        setChanged();
        notifyObservers(obj);
    }
}
