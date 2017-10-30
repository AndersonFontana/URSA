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
        
        System.out.println("Servidor");
        Integer porta = 2001;
        DatagramSocket s = new DatagramSocket(2001);
        while(true){
            byte [] rdados = new byte[100];
            byte [] sdados = new byte[100];
            DatagramPacket rPack = new DatagramPacket(rdados, rdados.length);
            s.receive(rPack);
            String processar = new String(rPack.getData()).trim();
            System.out.println(processar);
            processar = processar.toUpperCase();
            sdados = processar.getBytes();
            System.out.println(processar);
            DatagramPacket sPack = new DatagramPacket(sdados, sdados.length, rPack.getAddress(), rPack.getPort());
            s.send(sPack);
            if(processar.equals("DESLIGAR")){
                s.close();
                break;
            }
            System.out.println("resposta enviada");
        }
    }
    
}
