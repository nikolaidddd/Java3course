package jspappl;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Integer.parseInt;

public class BeanComponents {

    private String Answer;

    public  BeanComponents() {
        Answer = "";
    }

    public String getAnswer() {
        return Answer;
    }



    public void CalculateAnswer(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            int number = parseInt(args[i]);
            list.add(number);
        }
        Collections.sort(list);
        String answer ="";
        answer += "[";
        for (int i = args.length -1 ; i > 0; i--) {
            answer += list.get(i);
            answer += ",";
        }
        answer += list.get(0);
        answer += "]";
        this.Answer = answer;
    }

}
