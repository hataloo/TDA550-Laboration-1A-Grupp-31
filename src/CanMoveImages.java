import java.awt.*;
import java.awt.image.BufferedImage;

public interface CanMoveImages {
    public void moveImageLocation(int hashcode, Point point);
    public void loadImage(int hashcode, BufferedImage image, Point point);
}
