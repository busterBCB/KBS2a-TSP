package TSP1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Tekenpanel extends JPanel {

    Result result;

    public Tekenpanel() {
        setPreferredSize(new Dimension(320, 300));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        //magazijn maken
        g.setColor(Color.black);
        g.fillRect(0, 0, 1, 300);
        g.fillRect(0, 0, 320, 1);
        g.fillRect(319, 0, 1, 300);
        g.fillRect(0, 299, 320, 1);
        int aantal = 45;
        for (int i = 0; i < 6; i++) {
            g.fillRect(aantal, 0, 5, 300);
            aantal = aantal + 50;
        }
        aantal = 45;
        for (int i = 0; i < 5; i++) {
            g.fillRect(40, aantal, 265, 5);
            aantal = aantal + 50;
        }
        g.setColor(Color.red);
        // start punt
        g.fillOval(5, 255, 35, 35);

        int x0 = 0;
        int y0 = 0;
        if (result != null) {
            for (int i = 0; i < result.getRoute().size(); i++) {
                int x = 0;
                int y = 0;
                x = result.getRoute().get(i).getX();
                y = result.getRoute().get(i).getY();
                int x1 = (x * 50) + 5;
                int y1 = 300 - (((y + 1) * 50) - 5);
                g.setColor(Color.green);
                // pakketjes maken
                g.fillOval(x1, y1, 35, 35);
                if (i == 0) {
                    x0 = ((x * 50) + 5) + 35 / 2;
                    y0 = (300 + (35 / 2)) - (((y + 1) * 50) - 5);;
                    //g.setColor(Color.red);
                    //g.fillOval(5, 255, 35, 35);
                } else if (i == result.getRoute().size() - 1) {
                    g.setColor(Color.blue);
                    int x2 = result.getRoute().get(i - 1).getX();
                    int y2 = result.getRoute().get(i - 1).getY();
                    x1 = ((x * 50) + 5) + 35 / 2;
                    y1 = (300 + (35 / 2)) - (((y + 1) * 50) - 5);
                    x2 = ((x2 * 50) + 5) + 35 / 2;
                    y2 = (300 + (35 / 2)) - (((y2 + 1) * 50) - 5);
                    // lijn van een na laatste naar laatste pakketje
                    g.drawLine(x1, y1, x2, y2);
                    x1 = ((x * 50) + 5) + 35 / 2;
                    y1 = (300 + (35 / 2)) - (((y + 1) * 50) - 5);
                    //start punt en lijnen  naar startpunt
                    g.drawLine(22 + 1 / 2, 272 + 1 / 2, x1, y1);
                    g.setColor(Color.red);
                    g.fillOval(5, 255, 35, 35);
                    g.setColor(Color.blue);
                    g.drawLine(22 + 1 / 2, 272 + 1 / 2, x0, y0);
                } else {
                    // route weergeven
                    g.setColor(Color.blue);
                    int x2 = result.getRoute().get(i - 1).getX();
                    int y2 = result.getRoute().get(i - 1).getY();
                    x1 = ((x * 50) + 5) + 35 / 2;
                    y1 = (300 + (35 / 2)) - (((y + 1) * 50) - 5);
                    x2 = ((x2 * 50) + 5) + 35 / 2;
                    y2 = (300 + (35 / 2)) - (((y2 + 1) * 50) - 5);
                    g.drawLine(x1, y1, x2, y2);
                }

            }
        }

    }

    public void setResult(Result result) {
        this.result = result;
        repaint();
    }
}
