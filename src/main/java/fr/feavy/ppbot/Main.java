package fr.feavy.ppbot;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main extends JPanel {

    private final ImageRenderer imageRenderer;
    private final LogoCreator logoCreator;
    private DiscordAPI discordAPI;

    public Main() {
        super(new BorderLayout());

        File file = new File("authorization.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Veuillez entrer votre autorisation Discord dans le fichier.");
            System.exit(0);
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            String authorization = new String(fis.readAllBytes());
            this.discordAPI = new DiscordAPI(authorization);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logoCreator = new LogoCreator();
        imageRenderer = new ImageRenderer(logoCreator.next());

        this.add(imageRenderer, BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new BorderLayout());

        JButton nextButton = new JButton("Suivant");
        nextButton.addActionListener(e -> {
            this.imageRenderer.setImage(this.logoCreator.next());
        });
        southPanel.add(nextButton, BorderLayout.WEST);

        JButton validateButton = new JButton("Valider");
        validateButton.addActionListener(e -> {
            this.discordAPI.updateAvatar(this.imageRenderer.getImage());
        });
        southPanel.add(validateButton, BorderLayout.EAST);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Logo creator");
        jFrame.setContentPane(new Main());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }
}
