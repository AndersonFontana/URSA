/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import BANCO.DaoBanco;
import dominio.Oportunidade;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Pedro
 */
public class Processar extends Thread{
    byte [] sdados = new byte[100];

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
        //System.out.println(this.getName());
        String dados = new String(rPack.getData());
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
            Integer posicao = 0;
            byte [] rdados = new byte[100];
            DatagramPacket rPack2 = new DatagramPacket(rdados, rdados.length);
            try {
                soc.receive(rPack2);
            } catch (IOException ex) {
                Logger.getLogger(Processar.class.getName()).log(Level.SEVERE, null, ex);
            }
            String auxx = new String(rPack2.getData());
            posicao = (Integer.parseInt(auxx.split("\n")[0].trim()))-1;
            auxx = auxx.split("\n", 2)[1];
            Vetdados[posicao] = auxx;
        }
        String dadosOP = concat(Vetdados);
        switch(operacao){
            case "-i":
                System.out.println("incluir");
                incluir(dadosOP);
                break;
        }
    }
    public void parar(){
        rodando = false;
    }
    public String concat(String vet[]){
        String aux = new String();
        for(int i = 0; i < vet.length; i++){
            aux = aux + vet[i];
        }
        return aux;
    }
    public Oportunidade instanciaOP(String dados){
        Oportunidade op = new Oportunidade();
        op.setCodigo(Integer.parseInt(dados.split("\n")[0]));
        op.setDescricao(dados.split("\n")[1]);
        op.setCodcargo(Integer.parseInt(dados.split("\n")[2]));
        op.setAcesso(Integer.parseInt(dados.split("\n")[3]));
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date dt = null;
        try {
            dt = (Date)formatter.parse(dados.split("\n")[4]);
        } catch (ParseException ex) {
            Logger.getLogger(Processar.class.getName()).log(Level.SEVERE, null, ex);
        }
        op.setFechada(dt);
        return op;
    }
    public void incluir(String dados){
        Oportunidade op = instanciaOP(dados);
        DaoBanco dao = new DaoBanco();
        try {
            dao.adicionar(op);
        } catch (Exception ex) {
            Logger.getLogger(Processar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
