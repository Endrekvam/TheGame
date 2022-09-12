package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_TENT extends SuperObject {

    GamePanel gp;

    public OBJ_TENT(GamePanel gp) {

        this.gp = gp;

        name = "Tent";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/tent.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
