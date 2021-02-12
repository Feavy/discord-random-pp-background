package fr.feavy.ppbot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class LogoCreator {
    private final Random random = new Random();
    private BufferedImage logo;

    public LogoCreator() {
        try {
            logo = ImageIO.read(getClass().getResourceAsStream("/overlay.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage next() {
        BufferedImage newImg = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImg.createGraphics();
        g.setColor(getRandomColor());
        g.fillRect(0, 0, 512, 512);
        g.drawImage(this.logo, 0, 0, null);
        return newImg;
    }

    private Color getRandomColor() {
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

}
