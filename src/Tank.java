import java.awt.*;

public class Tank {
    private int x;
    private int y;
    private static final int SPEED = 10;
    private Dir dir = Dir.DOWN;
    TankFrame tankFrame = null;

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private boolean moving = false;

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        g.setColor(color);

        if (!moving) return;
        move();
    }

    private void move() {
        switch (this.dir) {
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
    }

    public void fire() {
        tankFrame.bullet = new Bullet(this.x, this.y, this.dir);
    }
}
