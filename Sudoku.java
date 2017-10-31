import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

public class Sudoku extends JPanel implements ActionListener
{
    private Game game;
    private int score;
    private String[][] entries;
    private JLabel clockLabel;
    private JLabel sudokuLabel;
    private JButton quitButton;
    private static final String[][] SOLUTION = {
            {"8", "3", "5", "4", "1", "6", "9", "2", "7"},
            {"2", "9", "6", "8", "5", "7", "4", "3", "1"},
            {"4", "1", "7", "2", "9", "3", "6", "5", "8"},
            {"5", "6", "9", "1", "3", "4", "7", "8", "2"},
            {"1", "2", "3", "6", "7", "8", "5", "4", "9"},
            {"7", "4", "8", "5", "2", "9", "1", "6", "3"},
            {"6", "5", "2", "7", "8", "1", "3", "9", "4"},
            {"9", "8", "1", "3", "4", "5", "2", "7", "6"},
            {"3", "7", "4", "9", "6", "2", "8", "1", "5"},
    };
    private static final int CLUSTER = 3;
    private static final int MAX_ROWS = 9;
    private static final float FIELD_PTS = 32f;
    private static final int GAP = 2;
    private static final Color BG = Color.LIGHT_GRAY;
    private entry[][] fieldGrid = new entry[MAX_ROWS][MAX_ROWS];
    private JPanel[][] panels;

    public Sudoku(Game game, int score)
    {
        this.game = game;
        this.score = score + 540;
        clockLabel = new JLabel("");
        sudokuLabel = new JLabel("Sudoku");
        sudokuLabel.setFont(new Font("Arial", Font.BOLD, 20));
        quitButton = new JButton("Quit");
        quitButton.setToolTipText("Exit to main menu.");
        entries = new String[MAX_ROWS][MAX_ROWS];
        clock();

        JPanel mainPanel = new JPanel(new GridLayout(CLUSTER, CLUSTER));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        mainPanel.setBackground(BG);
        panels = new JPanel[CLUSTER][CLUSTER];
        for (int i = 0; i < panels.length; i++)
        {
            for (int j = 0; j < panels[i].length; j++)
            {
                panels[i][j] = new JPanel(new GridLayout(CLUSTER, CLUSTER, 1, 1));
                panels[i][j].setBackground(BG);
                panels[i][j].setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
                mainPanel.add(panels[i][j]);
            }
        }

        for (int row = 0; row < fieldGrid.length; row++)
        {
            for (int col = 0; col < fieldGrid[row].length; col++)
            {
                fieldGrid[row][col] = new entry(game);
                int i = row / 3;
                int j = col / 3;
                panels[i][j].add(fieldGrid[row][col].entryGuess);
            }
        }


        fieldGrid[0][0].entryGuess.setText("8");
        fieldGrid[0][3].entryGuess.setText("4");
        fieldGrid[0][5].entryGuess.setText("6");
        fieldGrid[0][8].entryGuess.setText("7");
        fieldGrid[1][6].entryGuess.setText("4");
        fieldGrid[2][1].entryGuess.setText("1");
        fieldGrid[2][6].entryGuess.setText("6");
        fieldGrid[2][7].entryGuess.setText("5");
        fieldGrid[3][0].entryGuess.setText("5");
        fieldGrid[3][2].entryGuess.setText("9");
        fieldGrid[3][4].entryGuess.setText("3");
        fieldGrid[3][6].entryGuess.setText("7");
        fieldGrid[3][7].entryGuess.setText("8");
        fieldGrid[4][4].entryGuess.setText("7");
        fieldGrid[5][1].entryGuess.setText("4");
        fieldGrid[5][2].entryGuess.setText("8");
        fieldGrid[5][4].entryGuess.setText("2");
        fieldGrid[5][6].entryGuess.setText("1");
        fieldGrid[5][8].entryGuess.setText("3");
        fieldGrid[6][1].entryGuess.setText("5");
        fieldGrid[6][2].entryGuess.setText("2");
        fieldGrid[6][7].entryGuess.setText("9");
        fieldGrid[7][2].entryGuess.setText("1");
        fieldGrid[8][0].entryGuess.setText("3");
        fieldGrid[8][3].entryGuess.setText("9");
        fieldGrid[8][5].entryGuess.setText("2");
        fieldGrid[8][8].entryGuess.setText("5");

        fieldGrid[0][0].getEntryGuess().setEditable(false);
        fieldGrid[0][3].getEntryGuess().setEditable(false);
        fieldGrid[0][5].getEntryGuess().setEditable(false);
        fieldGrid[0][8].getEntryGuess().setEditable(false);
        fieldGrid[1][6].getEntryGuess().setEditable(false);
        fieldGrid[2][1].getEntryGuess().setEditable(false);
        fieldGrid[2][6].getEntryGuess().setEditable(false);
        fieldGrid[2][7].getEntryGuess().setEditable(false);
        fieldGrid[3][0].getEntryGuess().setEditable(false);
        fieldGrid[3][2].getEntryGuess().setEditable(false);
        fieldGrid[3][4].getEntryGuess().setEditable(false);
        fieldGrid[3][6].getEntryGuess().setEditable(false);
        fieldGrid[3][7].getEntryGuess().setEditable(false);
        fieldGrid[4][4].getEntryGuess().setEditable(false);
        fieldGrid[5][1].getEntryGuess().setEditable(false);
        fieldGrid[5][2].getEntryGuess().setEditable(false);
        fieldGrid[5][4].getEntryGuess().setEditable(false);
        fieldGrid[5][6].getEntryGuess().setEditable(false);
        fieldGrid[5][8].getEntryGuess().setEditable(false);
        fieldGrid[6][1].getEntryGuess().setEditable(false);
        fieldGrid[6][2].getEntryGuess().setEditable(false);
        fieldGrid[6][7].getEntryGuess().setEditable(false);
        fieldGrid[7][2].getEntryGuess().setEditable(false);
        fieldGrid[8][0].getEntryGuess().setEditable(false);
        fieldGrid[8][3].getEntryGuess().setEditable(false);
        fieldGrid[8][5].getEntryGuess().setEditable(false);
        fieldGrid[8][8].getEntryGuess().setEditable(false);

        for (int row = 0; row < fieldGrid.length; row++)
        {
            for (int col = 0; col < fieldGrid[row].length; col++)
            {
                if (!fieldGrid[row][col].entryGuess.isEditable())
                    fieldGrid[row][col].entryGuess.setToolTipText("This is here to help you!");
            }
        }


        JButton button = new JButton("Submit");
        button.addActionListener(this);
        button.setToolTipText("Check answers");

        setLayout(null);
        mainPanel.setBounds(125,20,340,340);
        button.setBounds(15,300,75,30);
        clockLabel.setBounds(400,0,200,20);
        clockLabel.setForeground(Color.WHITE);
        sudokuLabel.setBounds(5,2,80,32);
        sudokuLabel.setForeground(Color.WHITE);
        quitButton.setBounds(495,300,75,30);
        quitButton.addActionListener(this);
        add(quitButton);
        add(sudokuLabel);
        add(clockLabel);
        add(mainPanel);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource();
        JButton b = null;
        String buttonText = "";

        if (o instanceof JButton)
        {
            b = (JButton) o;
        }

        if (b != null)
        { //allows access to the button variable and can determine what action to take

            if (b.getText().equalsIgnoreCase("quit"))
            {
                try
                {
                    score -= 540;
                    game.frame.getContentPane().setVisible(false);
                    game.frame.getContentPane().remove(this);
                    game.frame.add(new ScoreScreen(this.game, score));
                    game.frame.getContentPane().setVisible(true);
                }
                catch (IOException e2)
                {
                    e2.getMessage();
                }
            }

            if (b.getText().equalsIgnoreCase("submit"))
            {
                boolean isWrong = false;
                for (int row = 0; row < fieldGrid.length; row++)
                {
                    for (int col = 0; col < fieldGrid[row].length; col++)
                    {
                        entries[row][col] = fieldGrid[row][col].getEntryGuess().getText();
                    }
                }

                for (int i = 0; i < entries.length; i++)
                {
                    for (int j = 0; j < entries[i].length; j++)
                    {
                        if (!entries[i][j].equals(SOLUTION[i][j]))
                        {
                            isWrong = true;
                            if (!fieldGrid[i][j].hasBeenGuessed)
                            {
                                score -= 10;
                                fieldGrid[i][j].hasBeenGuessed = true;
                            }
                        }
                    }
                }

                if (isWrong)
                {
                    JOptionPane.showMessageDialog(game.frame,
                            "Incorrect. Please try again.");
                }
                else
                {
                    try
                    {
                        game.frame.getContentPane().setVisible(false);
                        game.frame.getContentPane().remove(this);
                        game.frame.add(new ScoreScreen(this.game, score));
                        game.frame.getContentPane().setVisible(true);
                    }
                    catch (IOException e2)
                    {
                        e2.getMessage();
                    }
                }
            }
        }
    }

    public void clock()
    { //clock that runs throughout program
        Thread clock = new Thread()
        {
            public void run()
            {
                try
                {
                    for (;;)
                    {
                        Calendar cal = new GregorianCalendar();
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int month = cal.get(Calendar.MONTH) + 1;
                        int year = cal.get(Calendar.YEAR);

                        int second = cal.get(Calendar.SECOND);
                        int minute = cal.get(Calendar.MINUTE);
                        int hour = cal.get(Calendar.HOUR);

                        clockLabel.setText("Time " + hour + ":" + minute + ":" + second + "      Date " + month + "/" + day + "/" + year);
                        sleep(1000);
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        clock.start();
    }

    private class entry
    {
        private Game game;
        private JTextField entryGuess;
        private boolean hasBeenGuessed;

        entry(Game game)
        {
            this.game = game;
            hasBeenGuessed = false;
            entryGuess = createField();
            entryGuess.setToolTipText("Please type in a number");
        }

        JTextField createField() {
            JTextField field = new JTextField(2);
            field.setTransferHandler(null);
            field.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    if (field.getText().length() >= 1 ) // limit textfield to 1 characters
                        e.consume();
                    if (e.getKeyChar() >57 || e.getKeyChar() <49){
                        if(e.getKeyChar() != 8) {
                            JOptionPane.showMessageDialog(game.frame,
                                    "Please input a valid character");
                            e.consume();
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                        field.setText("");
                    }
                }
            });
            field.setHorizontalAlignment(JTextField.CENTER);
            field.setFont(field.getFont().deriveFont(Font.BOLD, FIELD_PTS));

            return field;
        }

        JTextField getEntryGuess()
        {
            return entryGuess;
        }

    }

    public void paintComponent(Graphics g) {
        ImageIcon dog = new ImageIcon("dog.jpg");
        super.paintComponent(g);
        dog.paintIcon(this,g,0,0);
    }
}
