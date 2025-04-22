package com.reporting;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClickUpNotifier {

    private static final String CLICKUP_API_URL = "https://api.clickup.com/api/v2/task/8696tphh5/comment"; // Replace with your task ID
    private static final String API_TOKEN = "pk_81764333_0NJU107ODZDSGOFAEJXJMYNVCOG8R9K0"; // Replace with your ClickUp API token


    public static void sendNotification(String message) {
        try {
            URL url = new URL(CLICKUP_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", API_TOKEN);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = "{\"comment\": \"" + message + "\"}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
