/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
//        int port = 1972;
//        try{
//            ServerSocket servidor = new ServerSocket (port);
//            System.out.println("Servidor escutando porta: "+servidor.getLocalPort());
//            
//            Socket cliente = servidor.accept();
//            
//            System.out.println("Servidor conectado com cliente "
//                                + cliente.getInetAddress().getHostAddress()+" na porta: "+ cliente.getPort());
//               
//            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
//            String rec = new String(entrada.readObject().toString());
//
//            System.out.println("Mensagem recebida: "+rec.toUpperCase());
//            entrada.close();
//            cliente.close();
//        }
//        catch(Exception e){
//            System.out.println("Erro: "+e.getMessage());
//            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
//        }
//        finally{
//            
//        
//        }

        try {
            int porta = 1972;
            ServerSocket servidor = new ServerSocket(porta);
            System.out.println("Servidor escutando porta: "+servidor.getLocalPort());
            
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Servidor conectado com cliente "
                                + cliente.getInetAddress().getHostAddress()+" na porta: "+ cliente.getPort());
               
                ThreadServidorTCP t1 = new ThreadServidorTCP(cliente);
                t1.start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    
}
