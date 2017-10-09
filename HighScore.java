import javax.swing.*;
import java.awt.*;

/**
 * Created by Apex on 10/8/17.
 */
public class HighScore extends JPanel {

    private JLabel[] highScoreArray = new JLabel[5]; //array of high scores where index 0 is highest.


    public HighScore(){
        //initialize new Labels into array.
        for(int i = 0;i<highScoreArray.length;i++){
            highScoreArray[i] = new JLabel("ABC.....000000");
        }

        //"Highscore" Label.
        JLabel hs = new JLabel("High Score");
        //container for white space and highscore label
        Container c1 = new Container();

        //setting the LayoutManager as box so that it appears as a list.
        c1.setLayout(new BoxLayout(c1,BoxLayout.PAGE_AXIS));

        //create whitespace between Panel and Label
        c1.add(Box.createRigidArea(new Dimension(0,50)));
        c1.add(hs);

        //create whitespace between Label and HighScore Labels
        c1.add(Box.createRigidArea(new Dimension(0,40)));


        //adds all highscores into the container.
        for(int i=0;i<highScoreArray.length;i++) {
            c1.add(highScoreArray[i]);
        }

        //set the font for all the labels
        hs.setFont(new Font("Arial", Font.BOLD, 32));
        for(int i=0;i<highScoreArray.length;i++) {
            highScoreArray[i].setFont(new Font("Arial", Font.BOLD, 16));
        }

        //add the container into the panel.
        this.add(c1);
    }
}
