import java.io.*;
import java.util.Observable;

public class WorkWithFile extends Observable{

    private static String _logPath;
    public static void SetLogPath(String logPath){
        _logPath = logPath;
    }


    public static void writeFile(String message){
        try{
            File file = new File(_logPath);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            try{
                bw.newLine();
                bw.write(message);
            }finally {
                bw.close();
            }

        }catch(IOException ex){
            writeFile(ex.getMessage(), "exceptions.txt");
        }
    }

    private static void writeFile(String message, String outPath){
        try{
            File file = new File(outPath);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            try{
                bw.newLine();
                bw.write(message);
            }finally {
                bw.close();
            }

        }catch(IOException ex){
            writeFile(ex.getMessage(), "exceptions.txt");
        }
    }
}
