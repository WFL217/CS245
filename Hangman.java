package swingv1.pkg0;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Hangman extends JPanel implements ActionListener{

    private static final long serialVersionUID = -3872551417773200878L;

    private JButton[] buttons;
    private JLabel scoreLabel;
    private int score;

    private JLabel clockLabel;
    private int mistakes =0;
    private JFrame frame;
    private String word;
    private String choice;

    public Hangman(){

        buttons = new JButton[26];
        clockLabel = new JLabel("");
        scoreLabel = new JLabel("Score: ");
        loadGUI();
        clock();
        word = "hello";


    }

    public void clock(){
        Thread clock = new Thread(){
            public void run(){
                try{
                    for(;;) {
                        Calendar cal = new GregorianCalendar();
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int month = cal.get(Calendar.MONTH)+1;
                        int year = cal.get(Calendar.YEAR);

                        int second = cal.get(Calendar.SECOND);
                        int minute = cal.get(Calendar.MINUTE);
                        int hour = cal.get(Calendar.HOUR);

                        clockLabel.setText("Time " + hour + ":" + minute + ":" + second + "      Date " + month + "/" + day + "/" + year);
                        sleep(1000);
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        clock.start();
    }

    public void loadGUI(){

        int ascii = 65;


        Container hold = new Container();
        hold.setLayout(new BoxLayout(hold,BoxLayout.PAGE_AXIS));
        hold.add(clockLabel);
        hold.add(scoreLabel);
        hold.add(Box.createRigidArea(new Dimension(50,280)));
        Container buttonContainer = new Container();
        hold.add(buttonContainer);
        buttonContainer.setLayout(new GridLayout(2,13,2,0));


        for(int i = 0;i<buttons.length;i++,ascii++) {

            buttons[i] = new JButton(Character.toString((char) ascii));
            buttons[i].setPreferredSize(new Dimension(30,30));
            buttons[i].setBorder(null);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 10));
            buttons[i].addActionListener(this);
            buttonContainer.add(buttons[i]);

        }

        add(hold);


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
        g2.fillRect(240, 70, 100, 5);


        //vertical bar
        g2.fillRect(240,70,5,160);

        //rope
        g2.fillRect(340,70,5,30);

        //horizontal bar
        g2.fillRect(120,230,320,5);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        JButton b = null;
        String buttonText = "";
        Graphics2D g2 = (Graphics2D)this.getGraphics();

        if(o instanceof JButton) {
            b = (JButton) o;
        }

        if( b!=null){
            setChoice(b.getText().toLowerCase());

            if(mistakes == 0 && !word.contains(getChoice()))
            {
                g2.drawOval(332,100,20,20);
                mistakes++;
                b.setVisible(false);
            }
            else if (mistakes == 1 && !word.contains(getChoice()))// draw body
            {
                g2.drawLine(342, 120, 342, 175);
                mistakes++;
                b.setVisible(false);
            }
            else if (mistakes == 2 && !word.contains(getChoice())) {
                g2.drawLine(342, 120, 322, 160);
                mistakes++;
                b.setVisible(false);
            }

            else if (mistakes == 3 && !word.contains(getChoice())) //draw right arm
            {
                g2.drawLine(342, 120, 362, 160);
                mistakes++;
                b.setVisible(false);
            }

            else if (mistakes == 4 && !word.contains(getChoice())) //draw left leg
            {
                g2.drawLine(342, 175, 330, 220);
                mistakes++;
                b.setVisible(false);
            }

            else if (mistakes == 5 && !word.contains(getChoice())) //draw right leg
            {
                g2.drawLine(342, 175, 353, 220);
                mistakes++;
                b.setVisible(false);
            }

            else if (mistakes <6 && word.contains(getChoice())){
                score+=100;
                scoreLabel.setText("Score: "+Integer.toString(score));
                b.setVisible(false);

            }

            }
    }

    public void setChoice(String s){
        choice = s;
    }

    public String getChoice(){
        return choice;
    }
}
