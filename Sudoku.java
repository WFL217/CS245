package swingv1.pkg0;

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
    private JLabel clockLabel;
    private JLabel sudokuLabel;
    private JButton quitButton;
    private static final int CLUSTER = 3;
    private static final int MAX_ROWS = 9;
    private static final float FIELD_PTS = 32f;
    private static final int GAP = 3;
    private static final Color BG = Color.BLACK;
    private static final Color SOLVED_BG = Color.LIGHT_GRAY;
    public static final int TIMER_DELAY = 2 * 1000;
    private JTextField[][] fieldGrid = new JTextField[MAX_ROWS][MAX_ROWS];

    public Sudoku(Game game, int score)
    {
        this.game = game;
        this.score = score;
        clockLabel = new JLabel("");
        sudokuLabel = new JLabel("Sudoku");
        sudokuLabel.setFont(new Font("Arial", Font.BOLD, 20));
        quitButton = new JButton("Quit");
        clock();

        JPanel mainPanel = new JPanel(new GridLayout(CLUSTER, CLUSTER));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        mainPanel.setBackground(BG);
        JPanel[][] panels = new JPanel[CLUSTER][CLUSTER];
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
                fieldGrid[row][col] = createField(row, col);
                int i = row / 3;
                int j = col / 3;
                panels[i][j].add(fieldGrid[row][col]);
            }
        }
        JButton button = new JButton(new SolveAction("Submit"));
        setLayout(null);
        mainPanel.setBounds(125, 20, 340, 340);
        button.setBounds(15, 300, 75, 30);
        clockLabel.setBounds(400, 0, 200, 20);
        sudokuLabel.setBounds(5, 2, 80, 32);
        quitButton.setBounds(495, 300, 75, 30);
        quitButton.addActionListener(this);
        add(quitButton);
        add(sudokuLabel);
        add(clockLabel);
        add(mainPanel);
        add(button);

    }

    private JTextField createField(int row, int col)
    {
        JTextField field = new JTextField(2);
        field.setTransferHandler(null);
        field.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                if (field.getText().length() >= 1) // limit textfield to 3 characters
                {
                    e.consume();
                }
                else if (e.getKeyChar() > 57 || e.getKeyChar() < 49)
                {
                    e.consume();
                }
            }
        });
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(field.getFont().deriveFont(Font.BOLD, FIELD_PTS));

        return field;
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

    private class SolveAction extends AbstractAction
    {

        public SolveAction(String name)
        {
            super(name);
            int mnemonic = (int) name.charAt(0);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {

            new Timer(TIMER_DELAY, new ActionListener()
            {
                private int i = 0;
                private int j = 0;

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    // MAX_ROWS is 9
                    if (i == MAX_ROWS)
                    {
                        ((Timer) e.getSource()).stop();
                    }
                    if (j == MAX_ROWS)
                    {
                        i++;
                        j = 0;
                    }
                    int number = (int) (MAX_ROWS * Math.random()) + 1;
                    fieldGrid[i][j].setBackground(SOLVED_BG);
                    fieldGrid[i][j].setText(String.valueOf(number));

                    j++;
                }
            }).start();
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
}
