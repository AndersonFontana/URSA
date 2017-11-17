/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package URSApp;

import dominio.Oportunidade;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author gpl1
 */
public class URSApp {

    private static final String SERVER_KEY = "AAAA1y5YfLg:APA91bGyqdMwpt2Z-NJoqkz22QTWpaM62_TDTgR3wF5uEFkRW8cNiGdPF9_QrGk-SKgLD85utZN3_8pmOU4AsFZTyRMuxSl4aj51ybEc1WOdUjJjeAUsFDP3BbvZBT4Nb1oHt5NO_A_F";
    private static final String DEVICE_TOKEN = "/topics/ursapp";
    
    public void sendPushNotification(Oportunidade objeto) {
        String title = "Nova vaga/oportunidade!";
        String message = "Você recebeu uma nova vaga ou oportunidade! Clique para visualizar.";
        
        String content = "{\"codigo\": \"" + objeto.getCodigo() + "\", " + 
                          "\"codcargo\": \"" + objeto.getCodcargo() + "\", " + 
                          "\"cargo\": \"" + objeto.getCargo().getDescricao() + "\", " + 
                          "\"descricao\": \"" + objeto.getDescricao() + "\", " + 
                          "\"acesso\": \"" + objeto.getAcesso() + "\", " + 
                          "\"ingresso\": \"" + objeto.getIngresso() + "\", " + 
                          "\"fechada\": \"" + objeto.getFechada() + "\"}";
        
        send(title, message, content);
    }
    
    private static void send(String title, String message, String content) {
        String pushMessage = "{\"data\": {" + 
                                        "\"title\": \"" + title + "\", " + 
                                        "\"message\": \"" + message + "\", " + 
                                        "\"content\": " + content + "}, " + 
                              "\"to\": \"" + DEVICE_TOKEN + "\"}";
        
        System.out.println(pushMessage);
        
        try {
            // Cria e configura conexão para enviar FCM Message request
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + SERVER_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            
            // Envia mensagem
            OutputStream os = conn.getOutputStream();
            os.write(pushMessage.getBytes());
            
            System.out.println(conn.getResponseCode());
            System.out.println(conn.getResponseMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

    }
    
}
