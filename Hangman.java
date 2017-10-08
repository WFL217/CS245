import javax.swing.*;
import java.awt.*;


public class Hangman extends JFrame{

    private static final long serialVersionUID = -3872551417773200878L;

    public Hangman(String title){
        Toolkit tk = Toolkit.getDefaultToolkit();
        JFrame frame = new JFrame(title);
        //JButton button = new JButton();
        MyPanel panel = new MyPanel();
        //JLabel header = new JLabel("CS245 Quarter Project");
        //JLabel credit = new JLabel("By Team FTP");

        //frame.setPreferredSize(new Dimension(width, height));
        //frame.setMaximumSize(new Dimension(width, height));
        //frame.setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(panel);
        frame.pack();


        /*header.setOpaque(true);
        header.setVerticalAlignment(JLabel.TOP);
        header.setHorizontalAlignment(JLabel.CENTER);


        credit.setOpaque(true);
        credit.setVerticalAlignment(JLabel.BOTTOM);
        credit.setHorizontalAlignment(JLabel.CENTER);
        credit.setFont(new Font("Arial", Font.BOLD, 12));*/

        //panel.add(header);
        //panel.add(credit);












    }

}
