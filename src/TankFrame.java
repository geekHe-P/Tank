import java.awt.*;
import java.awt.event.*;

public class TankFrame extends Frame {

    int tankX = 200;
    int tankY = 200;

    public TankFrame() {
       setSize(800,600);
       setResizable(false);
       setTitle("TankWar");
       setVisible(true);

       addKeyListener(new MyKeyListener());

       addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(tankX, tankY, 50, 50);
    }

    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            tankX += 10;
            repaint();
        }
    }
}
