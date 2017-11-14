/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author 152078
 */
public class Servidor {
    /**
     * @param args the command line arguments
     */
    public static final Logger LOGGER = Logger.getLogger( Processar.class.getName() );
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        Integer cont = 0;
        System.out.println("Servidor");
        int porta = 2001;
        DatagramSocket s = new DatagramSocket(porta);
         try {
            Handler console = new ConsoleHandler();
            Handler file = new FileHandler("C:\\Users\\JoãoPedro\\Desktop\\LOG_" + System.currentTimeMillis() +".txt"); //local do arquivo
            console.setLevel(Level.WARNING);
            file.setLevel(Level.ALL);
            file.setFormatter(new SimpleFormatter());//tipo do arquivo
            LOGGER.addHandler(file);
            LOGGER.addHandler(console);
            LOGGER.setUseParentHandlers(false);
        }
        catch(IOException io){
            LOGGER.warning("O Arquivo de LOGGER não pode ser criado");
        }
        while(true){
            byte [] rdados = new byte[100];
            byte [] sdados = new byte[100];
            DatagramPacket rPack = new DatagramPacket(rdados, rdados.length);
            s.receive(rPack);
            cont++;
            Processar p = new Processar(rPack, "Thread " + cont.toString(), porta+cont);
            p.start();
        }
    }
    
}
