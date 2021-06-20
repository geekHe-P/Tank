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
    private final double tankWidth = ResourceImage.tankD.getWidth();
    private final double tankHeight = ResourceImage.tankD.getHeight();
    private final double bulletWidth = ResourceImage.bullet.getWidth();
    private final double bulletHeight = ResourceImage.bullet.getHeight();

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int SPEED = 10;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    private Dir dir = Dir.DOWN;

    public void paint(Graphics g) {
        switch (dir) {
            case UP:
                g.drawImage(ResourceImage.bullet,  (int) (x+(tankWidth/2)-(bulletWidth/2)), (int) (y-bulletHeight), null);
                break;
            case DOWN:
                g.drawImage(ResourceImage.bullet,  (int) (x+(tankWidth/2)-(bulletWidth/2)), (int) (y+tankHeight), null);
                break;
            case LEFT:
                g.drawImage(ResourceImage.bullet,  (int) (x-bulletWidth), (int) (y+(tankHeight/2)-(bulletHeight/2)), null);
                break;
            case RIGHT:
                g.drawImage(ResourceImage.bullet,  (int) (x+tankWidth), (int) (y+(tankHeight/2)-(bulletHeight/2)), null);
                break;
        }

        move();
    }

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
    }
}
