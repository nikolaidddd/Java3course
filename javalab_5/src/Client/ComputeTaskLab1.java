package Client;

import Compute.Compute;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

public class ComputeTaskLab1 {
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Compute comp = (Compute) registry.lookup(name);
            String[] taskArray = Arrays.copyOfRange(args, 1, args.length);
            TaskLab1 task = new TaskLab1(taskArray);
            String resultString = comp.executeTask(task);
            System.out.println(resultString);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }
}