import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceImage {
    public static BufferedImage tankL;
    public static BufferedImage tankU;
    public static BufferedImage tankD;
    public static BufferedImage tankR;

    static {
        try {
            tankD = ImageIO.read(Objects.requireNonNull(ResourceImage.class.getClassLoader().getResourceAsStream("Image/p1tankD.gif")));
            tankL = ImageIO.read(Objects.requireNonNull(ResourceImage.class.getClassLoader().getResourceAsStream("Image/p1tankL.gif")));
            tankU = ImageIO.read(Objects.requireNonNull(ResourceImage.class.getClassLoader().getResourceAsStream("Image/p1tankU.gif")));
            tankR = ImageIO.read(Objects.requireNonNull(ResourceImage.class.getClassLoader().getResourceAsStream("Image/p1tankR.gif")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
