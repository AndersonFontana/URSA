/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Daniela
 */
public class ServidorTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        int port = 1972;
        try{
            ServerSocket servidor = new ServerSocket (port);
            System.out.println("Servidor escutando porta: "+servidor.getLocalPort());
            
            Socket cliente = servidor.accept();

            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            String rec = new String(entrada.readObject().toString());

            System.out.println("Mensagem recebida: "+rec.toUpperCase());
            entrada.close();
            cliente.close();
        }
        catch(Exception e){
            System.out.println("Erro: "+e.getMessage());
        }
        finally{
            
        
        }

    }
    
}