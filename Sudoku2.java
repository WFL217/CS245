import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Sudoku extends JPanel {
    private Game game;
    private int score;
    private static final int CLUSTER = 3;
    private static final int MAX_ROWS = 9;
    private static final float FIELD_PTS = 32f;
    private static final int GAP = 3;
    private static final Color BG = Color.BLACK;
    private static final Color SOLVED_BG = Color.LIGHT_GRAY;
    public static final int TIMER_DELAY = 2 * 1000;
    private JTextField[][] fieldGrid = new JTextField[MAX_ROWS][MAX_ROWS];

    public Sudoku(Game game, int score) {
        this.game = game;
        this.score = score;
        JPanel mainPanel = new JPanel(new GridLayout(CLUSTER, CLUSTER));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        mainPanel.setBackground(BG);
        JPanel[][] panels = new JPanel[CLUSTER][CLUSTER];
        for (int i = 0; i < panels.length; i++) {
            for (int j = 0; j < panels[i].length; j++) {
                panels[i][j] = new JPanel(new GridLayout(CLUSTER, CLUSTER, 1, 1));
                panels[i][j].setBackground(BG);
                panels[i][j].setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
                mainPanel.add(panels[i][j]);
            }
        }

        for (int row = 0; row < fieldGrid.length; row++) {
            for (int col = 0; col < fieldGrid[row].length; col++) {
                fieldGrid[row][col] = createField(row, col);
                int i = row / 3;
                int j = col / 3;
                panels[i][j].add(fieldGrid[row][col]);
            }
        }

        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        add(new JButton(new SolveAction("Solve")), BorderLayout.PAGE_END);
    }

    private JTextField createField(int row, int col) {
        JTextField field = new JTextField(2);
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(field.getFont().deriveFont(Font.BOLD, FIELD_PTS));

        return field;
    }

    private class SolveAction extends AbstractAction {

        public SolveAction(String name) {
            super(name);
            int mnemonic = (int) name.charAt(0);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new Timer(TIMER_DELAY, new ActionListener() {
                private int i = 0;
                private int j = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    // MAX_ROWS is 9
                    if (i == MAX_ROWS) {
                        ((Timer) e.getSource()).stop();
                    }
                    if (j == MAX_ROWS) {
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
}