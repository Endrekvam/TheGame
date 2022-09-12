package main;

import object.OBJ_KEY;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCount = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, gp.tileSize - (gp.tileSize/2) + (gp.tileSize/4));
        arial_80B = new Font("Arial", Font.BOLD, gp.tileSize - (gp.tileSize/2) + (gp.tileSize/4) * 2);
        OBJ_KEY key = new OBJ_KEY(gp);
        keyImage = key.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2) {

        if (gameFinished) {

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);

            text = "Congratulations";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Your time is: " + dFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*5);
            g2.drawString(text, x, y);

            gp.gameThread = null;


        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.originalTileSize * 2 - (gp.originalTileSize / 4), gp.originalTileSize * 2, gp.originalTileSize * 3, gp.originalTileSize * 3, null);
            g2.drawString("x " + gp.player.hasKey, gp.originalTileSize * 5, gp.originalTileSize * 4);

            // TIME
            playTime += (double)1/60;
            g2.drawString("Time:"+ dFormat.format(playTime), gp.tileSize*11, gp.tileSize*2);

            // MESSAGE
            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCount++;

                if (messageCount > 180) {
                    messageCount = 0;
                    messageOn = false;
                }
            }
        }
    }
}
