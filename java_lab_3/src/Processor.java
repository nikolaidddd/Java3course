import java.util.*;

public class Processor extends Observable{

    public FileNotify fileNotify = new FileNotify();

    public int[] SortingArr(String args[]) {
        ArrayList<Integer> al1 = new ArrayList<Integer>();

            for (String x : args) {
                notifyWithArray("Accessing Array -'args':" + x);
                al1.add(Integer.parseInt(x));
                Collections.sort(al1, Collections.reverseOrder());
            }

        int[] arr = al1.stream().mapToInt(i->i).toArray();
        return arr;
    }

    public void notifyWithArray(Object obj){
        setChanged();
        notifyObservers(obj);
        fileNotify.notifyWithFile("writing");

    }
}
