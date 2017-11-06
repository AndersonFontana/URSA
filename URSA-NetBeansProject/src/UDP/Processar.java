/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Processar extends Thread{
    byte [] sdados = new byte[100];
    byte [] rdados = new byte[100];
    DatagramSocket soc = null;
    DatagramPacket rPack = null;
    Boolean rodando = true;
    Integer porta;
    public String parar;

    public Processar(DatagramPacket pack, String name, Integer porta) throws SocketException {
        this.setName(name);
        this.rPack = pack;
        this.porta = porta;
    }
    @Override
    public void run(){
        try {
            soc = new DatagramSocket(porta);
        } catch (SocketException ex) {
            Logger.getLogger(Processar.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(this.getName());
        String dados = new String(rPack.getData());
        System.out.println(dados);
        String dadosRe = "Recebido";
        sdados = dadosRe.getBytes();
        DatagramPacket sPack = new DatagramPacket(sdados, sdados.length, rPack.getAddress(),rPack.getPort()); 
        try {
            soc.send(sPack);
        } catch (IOException ex) {
            Logger.getLogger(Processar.class.getName()).log(Level.SEVERE, null, ex);
        }
        String operacao = dados.split("\n")[1].trim();
        String aux = dados.split("\n")[2].trim();
        Integer tam = Integer.parseInt(aux);
        String Vetdados [] = new String[tam];
        for(int i = 0; i< tam; i++){
            DatagramPacket rPack2 = new DatagramPacket(rdados, rdados.length);
            try {
                soc.receive(rPack2);
            } catch (IOException ex) {
                Logger.getLogger(Processar.class.getName()).log(Level.SEVERE, null, ex);
            }
            String auxx = new String(rPack2.getData());
            Vetdados[i] = auxx;
            System.out.println(auxx);
        }
        switch(operacao){
            case "-i":
                //incluir();
                break;
        }
    }
    public void parar(){
        rodando = false;
    }
    
}
