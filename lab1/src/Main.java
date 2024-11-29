import java.util.*;


public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> al1 = new ArrayList<Integer>();
        for (String x : args) {
            al1.add(Integer.parseInt(x));
            Collections.sort(al1, Collections.reverseOrder());
        }
        System.out.println(al1);
    }
}
