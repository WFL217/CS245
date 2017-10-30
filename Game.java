/***************************************************************
 * file: Game.java
 * author: Team FTP
 * class: CS 245
 *
 * assignment: Swing Project v1.2
 * date last modified: 10/19/17
 *
 * purpose: This is the main method which ties all other panels into
 * one frame.
 *
 ****************************************************************/

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Game {

    final int FINAL_WIDTH = 600; //frame dimensions
    final int FINAL_HEIGHT = 400;

    JFrame frame;
    MyPanel intro;
    Credits credits;
    Menu menu;
    HighScore hs;
    ScoreScreen ss;
    Hangman hg;
    ButtonGame bg;

    public enum STATE{ //allows access to each panel
        Intro,
    }

    public static STATE gameState = STATE.Intro; //starts game in intro


    public Game(){ //initializes each panel upon creating the frame

        frame = new JFrame("CS245 Project");
        intro = new MyPanel(this);
        hs = new HighScore(this);
        hg = new Hangman(this);
        menu = new Menu(this);
        credits = new Credits(this);

        frame.setPreferredSize(new Dimension(FINAL_WIDTH, FINAL_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.add(intro);
        frame.setVisible(true);

        if(gameState == STATE.Intro) { //create game state with intro screen

            try {
                TimeUnit.SECONDS.sleep(3); //hold title for 3 second
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }

            frame.getContentPane().setVisible(false);
            frame.getContentPane().remove(intro);
            frame.add(menu); //start
            frame.getContentPane().setVisible(true);

        }


    }



    public static void main(String[] args) {
        new Game();
    } //run game

}