import java.awt.*;

public class Bullet {
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    private int x;
    private int y;
    public TankFrame tankFrame;
    private boolean live = true;
    private Group group = Group.BAD;

    private final int tankWidth = ResourceImage.tankD.getWidth();
    private final int tankHeight = ResourceImage.tankD.getHeight();
    private final int bulletWidth = ResourceImage.bullet.getWidth();
    private final int bulletHeight = ResourceImage.bullet.getHeight();

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int SPEED = 15;

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    private Dir dir = Dir.DOWN;

    //绘制子弹
    public void paint(Graphics g) {
        if (!live)
            tankFrame.bulletList.remove(this);

        switch (dir) {
            case UP:
                g.drawImage(ResourceImage.bullet, x+(tankWidth/2)-(bulletWidth/2), y-bulletHeight, null);
                break;
            case DOWN:
                g.drawImage(ResourceImage.bullet, x+(tankWidth/2)-(bulletWidth/2), y+tankHeight, null);
                break;
            case LEFT:
                g.drawImage(ResourceImage.bullet, x-bulletWidth, y+(tankHeight/2)-(bulletHeight/2), null);
                break;
            case RIGHT:
                g.drawImage(ResourceImage.bullet, x+tankWidth, y+(tankHeight/2)-(bulletHeight/2), null);
                break;
        }

        move();
    }

    //移动判断
    private void move() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y< 20 || x > tankFrame.getWidth() || y > tankFrame.getHeight()) {
            live = false;
        }
    }

    //碰撞检测
    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return;
        Rectangle rectangle1 = null;
        switch (dir) {
            case UP:
               rectangle1 = new Rectangle(x+(tankWidth/2)-(bulletWidth/2), y-bulletHeight, bulletWidth, bulletHeight);
                break;
            case DOWN:
                rectangle1 = new Rectangle(x+(tankWidth/2)-(bulletWidth/2), y+tankHeight, bulletWidth, bulletHeight);
                break;
            case LEFT:
                rectangle1 = new Rectangle(x-bulletWidth, y+(tankHeight/2)-(bulletHeight/2), bulletWidth, bulletHeight);
                break;
            case RIGHT:
                rectangle1 = new Rectangle(x+tankWidth, y+(tankHeight/2)-(bulletHeight/2), bulletWidth, bulletHeight);
                break;
            default:
                break;
        }
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), tankWidth, tankHeight);
        if (rectangle2.intersects(rectangle1)) {
            tank.die();
            this.die();
        }
    }

    //死亡判断
    private void die() {
        live = false;
    }
}
