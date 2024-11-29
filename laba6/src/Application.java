import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class Application extends Frame implements Observer, ActionListener, ItemListener {
    private static final int countObject = 3;
    private int nowCountObject;

    private LinkedList LL = new LinkedList();
    private LinkedList List_text = new LinkedList();

    private Color col;
    private Frame f;
    private Button b;

    private Button updateNumBtn;

    private Button updateSpeedBtn;
    private Choice colorChoice;

    private Label activeObjectLabel;
    private Label numLabel;
    private Label newNumLabel;

    private Label newSpeedLabel;

    private TextField activeObject;
    private Choice speedChoice;

    private Choice newSpeedChoice;
    private Choice textChoice;
    private TextField numField;

    private TextField newNumField;

    private  Label speedLabel;
    private  TextField speedText;

    private  JColorChooser tcc;

    Application(){
        this.addWindowListener(new WindowAdapter2());
        f = new Frame();
        f.setSize(new Dimension(300,100));
        f.setTitle("УО");
        f.setLayout(new GridLayout(2, 5));
        f.addWindowListener(new WindowAdapter2());
        b = new Button("Запуск");
        b.setSize(new Dimension(10,40));
        b.setActionCommand("START");
        b.addActionListener(this);
        f.add(b, new Point(20,20));

        colorChoice = new Choice();
        colorChoice.addItem("Синий");
        colorChoice.addItem("Зелёный");
        colorChoice.addItem("Красный");
        colorChoice.addItem("Чёрный");
        colorChoice.addItem("Жёлтый");
        colorChoice.addItemListener(this);
        f.add(colorChoice, new Point(60,20));

        textChoice = new Choice();
        textChoice.addItem("Привет");
        textChoice.addItem("Мир");
        textChoice.addItem("Сделка");
        textChoice.addItem("Тысяча");
        textChoice.addItem("Как дела?");
        f.add(textChoice);

        speedLabel = new Label("Скорость");
        f.add(speedLabel);
        speedText = new TextField();
        f.add(speedText);


        activeObjectLabel = new Label("Номер \n объекта:");
        f.add(activeObjectLabel);

        activeObject = new TextField();
        f.add(activeObject);


        newSpeedLabel = new Label("Cкорость");
        f.add(newSpeedLabel);
        newSpeedChoice = new Choice();
        newSpeedChoice.addItem("1");
        newSpeedChoice.addItem("4");
        newSpeedChoice.addItem("9");
        newSpeedChoice.addItem("16");
        newSpeedChoice.addItem("25");
        f.add(newSpeedChoice);
        updateNumBtn = new Button("Обновить");
        updateNumBtn.setActionCommand("UpdateSpeed");
        updateNumBtn.addActionListener(this);
        f.add(updateNumBtn);




        f.setVisible(true);
        this.setSize(500,300);
        this.setVisible(true);
        this.setLocation(100, 300);
    }

    public void update(Observable o, Object arg) {
        repaint();
    }

    public void paint (Graphics g) {
        if (!LL.isEmpty()){
            for (Object LL1 : LL) {
                TextObject text = (TextObject) LL1;
                g.setColor(text.col);
                g.drawString(text._text + " " + text._numObj, text.x, text.y);
            }
        }
    }
    public void itemStateChanged (ItemEvent iE) {}

    public void actionPerformed (ActionEvent aE) {
        String str = aE.getActionCommand();
        if (str.equals ("START")){

            if (nowCountObject == countObject){
                System.out.println("Кол-во объектов превышено");
                return;
            }


            switch (colorChoice.getSelectedIndex()) {
                case 0: col= Color.blue; break;
                case 1: col= Color.green; break;
                case 2: col= Color.red; break;
                case 3: col= Color.black; break;
                case 4: col= Color.yellow; break;
            }

//            col = tcc.getColor();

            int numberObject = ++nowCountObject;

            for (Object text : LL) {
                TextObject newText = (TextObject)text;
                if(newText._numObj == numberObject) {
                    System.out.println("Такой номер фигуры уже существует");
                    return;
                }
            }
            TextObject textObject = new TextObject(col, this.textChoice.getSelectedItem(),
                    Integer.parseInt(this.speedText.getText()), numberObject, this);
            LL.add(textObject);
            List_text.add(textObject._text + textObject._numObj);

            textObject.addObserver(this);

        } else if (str.equals ("UpdateSpeed")) {

            for (Object text : LL) {
                TextObject txt = (TextObject) text;
                if (txt._numObj == Integer.parseInt(activeObject.getText())) {
                    txt._speed = Integer.parseInt(newSpeedChoice.getSelectedItem());
                }
            }
        }
        repaint();
    }
}

class WindowAdapter2 extends WindowAdapter {
    public void windowClosing (WindowEvent wE) {System.exit (0);}
}