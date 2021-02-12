package fr.feavy.ppbot;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageRenderer extends JComponent {
    private BufferedImage image;

    public ImageRenderer(BufferedImage image) {
        this.image = image;
    }

    @Override
    public Dimension getSize() {
        return new Dimension(512, 512);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(512, 512);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage next) {
        this.image = next;
        this.repaint();
    }
}
