import java.awt.*;
import java.awt.event.*;

public class TankFrame extends Frame {

    boolean bR = false;
    boolean bL = false;
    boolean bU = false;
    boolean bD = false;
    Dir dir = Dir.DOWN;

    Tank tank = new Tank(200, 200, dir);
    Bullet bullet = new Bullet(30, 30, dir);

    public TankFrame() {
       setSize(800,600);
       setResizable(false);
       setTitle("TankWar");
       setVisible(true);

        System.out.println("sadf");
        System.out.println("sadf");

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
       tank.paint(g);
       bullet.paint(g);
    }

    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bD && !bR && !bU)
                tank.setMoving(false);
            else {
                tank.setMoving(true);
                if (bL)
                    tank.setDir(Dir.LEFT);
                if (bR)
                    tank.setDir(Dir.RIGHT);
                if (bD)
                    tank.setDir(Dir.DOWN);
                if (bU)
                    tank.setDir(Dir.UP);
            }
        }
    }
}
