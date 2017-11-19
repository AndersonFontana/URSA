/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author user
 */
public class ServidorTCP {

    /**
     * @param args the command line arguments
     */
    public static final Logger LOGGER = Logger.getLogger( ThreadServidorTCP.class.getName() );
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        int porta = 1972;
        ServerSocket servidor = new ServerSocket(porta);
        System.out.println("Servidor escutando porta: "+servidor.getLocalPort());
        
        try {
            Handler console = new ConsoleHandler();
            Handler file = new FileHandler("C:\\Users\\user\\Desktop\\URSA\\Logs\\LOG" + System.currentTimeMillis() +".txt"); //local do arquivo
            console.setLevel(Level.WARNING);
            file.setLevel(Level.ALL);
            file.setFormatter(new SimpleFormatter()); // tipo do arquivo
            LOGGER.addHandler(file);
            LOGGER.addHandler(console);
            LOGGER.setUseParentHandlers(false);
        }
        catch(IOException io){
            LOGGER.warning("O Arquivo de LOGGER não pode ser criado");
        }
            
        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Servidor conectado com cliente "
                            + cliente.getInetAddress().getHostAddress()+" na porta: "+ cliente.getPort());

            ThreadServidorTCP t1 = new ThreadServidorTCP(cliente);
            t1.start();
        }
    } 
    
}
    

