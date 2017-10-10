package swingv1.pkg0;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


class MyPanel extends JPanel {

    private int rectX = this.getWidth()/2;
    private int rectY = this.getHeight()/2;
    private int rectW = 32;
    private int rectH = 32;

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                moveSquare(e.getX(),e.getY());
            }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font f = new Font("Arial", Font.BOLD, 32);
        Font f2 = new Font("Arial", Font.BOLD, 16);
        Graphics2D g2 = (Graphics2D)g;
        g2.setFont(f);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawString("CS245 Project",this.getWidth()/3,50);
        g2.setFont(f2);
        g2.drawString("By Team FTP",this.getWidth()-350,this.getHeight()-32);

        g.setColor(Color.blue);
        g.fillRect(rectX,rectY,rectW,rectH);


    }

    public void moveSquare(int x, int y){
        int OFFSET = 1;

        if (rectX != x || rectY != y){
            repaint(rectX,rectY,rectW+OFFSET,rectH + OFFSET);
            rectX = x;
            rectY = y;
            repaint(rectX,rectY,rectW+OFFSET,rectH + OFFSET);
        }
    }


}
