import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;
import java.util.*;

public class Reader {

    public static String readConsoleString(){
        Scanner sc = new Scanner(System.in);
        String resultStr = "";
        if(sc.hasNext()){
            resultStr = sc.next();

        }
        return resultStr;
    }

    public static String readFilePath(){
        String path = readConsoleString();
        return path;
    }
    public static String[] readConsoleStringArgs(){
        Scanner sc = new Scanner(System.in);
        String resultStr;
        resultStr = sc.nextLine();
        String[] resultStrArr = resultStr.split(" ");

        return resultStrArr;
    }

    public static String[] readFileStringArgs(String inPath){
        String[] resultStrArr;
        try{
            BufferedReader br = new BufferedReader (new FileReader(inPath));

            String inStr;
            try{
                if((inStr = br.readLine()) != null){
                    resultStrArr = inStr.split(" ");
                    return resultStrArr;
                }
            }finally{
                br.close();
            }
        } catch (IOException ex) {

        }

        resultStrArr = new String[]{};
        return resultStrArr;
    }
}
