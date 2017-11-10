/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package URSApp;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author gpl1
 */
public class URSApp {

    private static String SERVER_KEY = "AAAA1y5YfLg:APA91bGyqdMwpt2Z-NJoqkz22QTWpaM62_TDTgR3wF5uEFkRW8cNiGdPF9_QrGk-SKgLD85utZN3_8pmOU4AsFZTyRMuxSl4aj51ybEc1WOdUjJjeAUsFDP3BbvZBT4Nb1oHt5NO_A_F";
    private static String DEVICE_TOKEN = "/topics/ursapp";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String title = "Título notificação";
        String message = "Mensagem notificação";
        
        try {
            sendPushNotification(title, message);
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    
    private static void sendPushNotification(String title, String message) throws Exception {
        String pushMessage = "{\"data\":{\"title\":\"" +
                title +
                "\",\"message\":\"" +
                message +
                "\"},\"to\":\"" +
                DEVICE_TOKEN +
                "\"}";
        System.out.println(pushMessage);
        
        // Cria conexão para enviar FCM Message request
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        os.write(pushMessage.getBytes());

        System.out.println(conn.getResponseCode());
        System.out.println(conn.getResponseMessage());
    }
    
}
