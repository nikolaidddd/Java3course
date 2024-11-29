package ThirdLab;

import java.util.LinkedList;

public class MyMath {
    public static String start(String s) {

        MyMath object = new MyMath();

        String result;

        try {
            result = Float.toString(object.make(s));
        } catch (Exception e) {
            result = "Bad expression";
        }


        return result;

    }

    public boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '/' || c == '%' || c == '*';
    }

    public boolean interval(char c) {
        return c == ' ';
    }

    public int opearatorsPriority(char operand) {
        switch (operand) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            default:
                return -1;
        }
    }

    public void operator(LinkedList<Float> cislo, char znak) {
        Float r = cislo.removeLast();
        Float l = cislo.removeLast();
        switch (znak) {
            case '+':
                cislo.add(l + r);
                break;
            case '-':
                cislo.add(l - r);
                break;
            case '*':
                cislo.add(l * r);
                break;
            case '/':
                cislo.add(l / r);
                break;
            case '%':
                cislo.add(l % r);
                break;
        }
    }

    public Float make(String s) {
        MyMath obj = new MyMath();
        LinkedList<Float> h = new LinkedList<Float>();
        LinkedList<Character> op = new LinkedList<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (obj.interval(c))
                continue;

            if (c == '(') {
                op.add('(');
            } else if (c == ')') {
                while (op.getLast() != '(')
                    operator(h, op.removeLast());
                op.removeLast();
            } else if (obj.isOperation(c))
            {
                while (!op.isEmpty() && obj.opearatorsPriority(op.getLast()) >= obj.opearatorsPriority(c))

                    operator(h, op.removeLast());
                op.add(c);
            } else {
                String operand = "";
                while (i < s.length() && Character.isDigit(s.charAt(i)))

                    operand += s.charAt(i++);
                --i;
                h.add(Float.parseFloat(operand));
            }
        }

        while (!op.isEmpty())
            operator(h, op.removeLast());
        return h.get(0);

    }
}
