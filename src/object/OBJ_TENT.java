package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_TENT extends SuperObject {

    public OBJ_TENT() {

        name = "Tent";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/objects/tent.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
