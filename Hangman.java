package swingv1.pkg0;

import javax.swing.*;
import java.awt.*;


public class Hangman extends JPanel{

    private static final long serialVersionUID = -3872551417773200878L;

    private JButton[] buttons = new JButton[10];
    private int mistakes =0;
    private JFrame frame;

    public Hangman(){
        loadGUI();


    }

    public void loadGUI(){
/*
        int ascii = 65;
        Container c3 = new Container();
        c3.setLayout(new BoxLayout(c3,BoxLayout.LINE_AXIS));
        //JPanel buttonPanel = new JPanel(new GridLayout(2,13));

        for(int i = 0;i<buttons.length;i++,ascii++) {

            buttons[i] = new JButton(Character.toString((char) ascii));
             buttons[i].setPreferredSize(new Dimension(30,30));
            c3.add(buttons[i]);

        }




        add(c3);

*/
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font f = new Font("Arial", Font.BOLD, 32);
        Font f2 = new Font("Arial", Font.BOLD, 16);
        Graphics2D g2 = (Graphics2D)g;
        g2.setFont(f);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawString("Hangman",0,25);

        //horizontal bar
        g2.fillRect(240,70,100,5);

        //vertical bar
        g2.fillRect(240,70,5,160);

        //rope
        g2.fillRect(340,70,5,30);

        //horizontal bar
        g2.fillRect(120,230,320,5);


        //head
        g2.drawOval(332,100,20,20);

        //body
        g2.drawLine(342,120,342,175);

        //left arm
        g2.drawLine(342, 120, 322, 160);

        //right arm
        g2.drawLine(342, 120, 362, 160);

        //left leg
        g2.drawLine(342, 175, 330, 220);

        //right leg
        g2.drawLine(342, 175, 353, 220);


    }


    public void drawPart(Graphics g){

    }

}
