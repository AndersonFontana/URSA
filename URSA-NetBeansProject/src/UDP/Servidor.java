/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author 152078
 */
public class Servidor {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        Integer cont = 0;
        System.out.println("Servidor");
        int porta = 2001;
        DatagramSocket s = new DatagramSocket(porta);
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
