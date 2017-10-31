/**
 *  file: Menu.java
 *  author: Team FTP
 *  class: CS 245 - Programming Graphical User Interfaces
 *
 * assignment: Swing Project v1.2
 * date last modified: 10/19/17
 *
 *  purpose: This class displays the menu to the user after the
 *  introduction screen has displayed for 3 seconds.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Menu extends JPanel implements ActionListener{

    //Private variables for the buttons
    private JButton playButton;
    private JButton highScoreButton;
    private JButton creditsButton;
    private JLabel hangmanLabel;
    private Game game;
    private HighScore hs;

    String choice = "";

    //Constructor that initializes buttons and creates listeners for the buttons
    public Menu(Game game){
        this.game = game;
        highScoreButton = new JButton("Highscores");
        playButton = new JButton("Play");
        creditsButton = new JButton("Credits");
        hangmanLabel = new JLabel("<html><font color='red'>H</font><font color='orange'>a</font><font color='yellow'>n</font><font color='green'>g</font><font color='blue'>m</font><font color='purple'>a</font><font color='#FF00FF'>n</font></html>");
        playButton.setToolTipText("Play the game");
        highScoreButton.setToolTipText("Click this to go to the high score page");
        creditsButton.setToolTipText("Click this to go to the credits page");
        playButton.addActionListener(this);
        highScoreButton.addActionListener(this);
        creditsButton.addActionListener(this);

        setLayout(null);
        add(hangmanLabel);
        hangmanLabel.setFont(new Font("Impact", Font.BOLD, 64));
        hangmanLabel.setBounds(160,40,300,70);

        playButton.setBounds(200,210,200,30);
        highScoreButton.setBounds(200,240,200,30);
        creditsButton.setBounds(200,270,200,30);
        add(playButton);
        add(highScoreButton);
        add(creditsButton);
/*
        Container c = new Container();

        c.add(hangmanLabel);

        c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
        c.add(Box.createRigidArea(new Dimension(0, 150)));
        c.add(playButton);
        c.add(highScoreButton);
        c.add(creditsButton);

        add(c);*/
    }

    //Method to wait for actions
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        JButton b = null;
        String buttonText = "";

        if(o instanceof JButton) {
            b = (JButton) o;
        }

        if(b!=null){
            //If "play" is pressed, begin Hangman
            if(b.getText().equalsIgnoreCase("play")){

                game.frame.getContentPane().setVisible(false);
                game.frame.getContentPane().remove(this);
                game.frame.add(new Hangman(game));
                game.frame.getContentPane().setVisible(true);
            }
            //If "highscores" is pressed, display highscores
            if(b.getText().equalsIgnoreCase("highscores")){
                game.frame.getContentPane().setVisible(false);
                game.frame.getContentPane().remove(this);
                try {
                    game.frame.add(new HighScore(game));
                }
                catch(IOException err){
                    err.getMessage();
                }
                game.frame.getContentPane().setVisible(true);
            }
            //If "credits" is pressed, display credits
            if(b.getText().equalsIgnoreCase("credits")){
                game.frame.getContentPane().setVisible(false);
                game.frame.getContentPane().remove(this);
                game.frame.add(new Credits(game));
                game.frame.getContentPane().setVisible(true);
            }
        }
    }

    public void paintComponent(Graphics g) {
        ImageIcon dog = new ImageIcon("dog.jpg");
        super.paintComponent(g);
        dog.paintIcon(this,g,0,0);
    }
}
