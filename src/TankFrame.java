import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TankFrame extends Frame {

    boolean bR = false;
    boolean bL = false;
    boolean bU = false;
    boolean bD = false;
    Dir dir = Dir.DOWN;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    Tank tank = new Tank(200, 400, dir, this);
    ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
    ArrayList<Tank> tankList = new ArrayList<Tank>();
    //Bullet bullet = new Bullet(30, 30, dir);

    //新建窗口
    public TankFrame() {
       setSize(WIDTH,HEIGHT);
       setResizable(false);  //不可缩放
       setTitle("TankWar");
       setVisible(true);

       addKeyListener(new MyKeyListener());

       addWindowListener(new WindowAdapter() {  //关闭窗口
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    //双缓冲解决闪烁问题
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(WIDTH, HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, WIDTH, HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    //绘画坦克和子弹
    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量：" + bulletList.size(), 10, 50);
        g.drawString("x坐标：" + tank.getX(), 10, 70);
        g.drawString("y坐标：" + tank.getY(), 10, 90);
        g.setColor(color);

       tank.paint(g);
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(g);
        }

        for (Bullet bullet : bulletList){
            for (Tank tank : tankList){
                bullet.collideWith(tank);
            }
        }
    }

    //键盘监听
    class MyKeyListener extends KeyAdapter {
        //按下键盘
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

        //弹起键盘
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
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        //建立主坦克方向
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
