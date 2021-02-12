package fr.feavy.ppbot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class DiscordAPI {
    private final String authorization;

    public DiscordAPI(String authorization) {
        this.authorization = authorization;
    }

    public void updateAvatar(BufferedImage newAvatar) {
        HttpClient client = HttpClient.newBuilder().build();

        String payload = "{\"avatar\":\"data:image/png;base64," + imageToBase64(newAvatar) + "\"}";

        HttpRequest.BodyPublisher bodyPublisher = HttpRequest.BodyPublishers.ofString(payload);

        HttpRequest request = HttpRequest.newBuilder(URI.create("https://discordapp.com/api/v6/users/@me"))
                .method("PATCH", bodyPublisher)
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")
                .header("origin", "https://discordapp.com")
                .header("authority", "discordapp.com")
                .header("accept", "*/*")
                .header("referer", "https://discordapp.com/activity")
                .header("content-type", "application/json")
                .header("x-super-properties", "eyJvcyI6IldpbmRvd3MiLCJicm93c2VyIjoiQ2hyb21lIiwiZGV2aWNlIjoiIiwiYnJvd3Nlcl91c2VyX2FnZW50IjoiTW96aWxsYS81LjAgKFdpbmRvd3MgTlQgMTAuMDsgV2luNjQ7IHg2NCkgQXBwbGVXZWJLaXQvNTM3LjM2IChLSFRNTCwgbGlrZSBHZWNrbykgQ2hyb21lLzc5LjAuMzk0NS4xMzAgU2FmYXJpLzUzNy4zNiIsImJyb3dzZXJfdmVyc2lvbiI6Ijc5LjAuMzk0NS4xMzAiLCJvc192ZXJzaW9uIjoiMTAiLCJyZWZlcnJlciI6IiIsInJlZmVycmluZ19kb21haW4iOiIiLCJyZWZlcnJlcl9jdXJyZW50IjoiIiwicmVmZXJyaW5nX2RvbWFpbl9jdXJyZW50IjoiIiwicmVsZWFzZV9jaGFubmVsIjoic3RhYmxlIiwiY2xpZW50X2J1aWxkX251bWJlciI6NTI4NzgsImNsaWVudF9ldmVudF9zb3VyY2UiOm51bGx9")
                .header("authorization", authorization)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String imageToBase64(BufferedImage image) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "png", os);
            return Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (Exception ignored) {

        }
        return null;
    }
}
