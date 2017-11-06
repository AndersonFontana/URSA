/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import dominio.Oportunidade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.JSONParser;


/**
 *
 * @author João Pedro
 */

public class Cliente {
    public static Scanner ler = new Scanner(System.in);
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException{
        // TODO code application logic here
        System.out.println("Cliente");
        
        System.out.println("Para ver os comandos digite (-help)");
        
        String operacao = null;
        while(true){
            operacao = null;
            String dados = null;
            String[] vetor;
            System.out.print("Insira a Operação: ");
            operacao = ler.next();
            if(operacao.equals("-help")){
                help();
                continue;
            }
            else if(operacao.equals("-i")){
                dados = incluir();
            }
            else if(operacao.equals("-a")){
                help();
            }
            else if(operacao.equals("-e")){
                help();
            }
            else if(operacao.equals("-c")){
                help();
            }
            else if(operacao.equals("-q")){
                break;
            }
            else{
                System.out.println("Comando Invalido");
                continue;
            }
            byte  sdados[] = new byte[100];
            operacao = String.format("%03d",0) + "\n" + operacao;
            vetor = Serializar(dados);
            Integer tamanho = vetor.length;
            int porta = enviar(operacao + "\n" + tamanho, 2001);
            for(int i = 0; i < vetor.length; i++){
                enviar(vetor[i], porta);
            }
            
        }
    }
    
    public static void help(){
        System.out.println("-i: inclui oportunidade");
        System.out.println("-a: altera oportunidade");
        System.out.println("-e: exclui oportunidade");
        System.out.println("-c: consulta oportunidade");
        System.out.println("-q: sair");
    }
    public static String incluir(){
        String retorno = null;
        Oportunidade op = new Oportunidade();
        System.out.print("Digite o Codigo:");
        op.setCodigo(ler.nextInt());
        System.out.print("Digite a Descricao:");
        try {
            op.setDescricao(in.readLine());
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("Digite o codigo do cargo:");
        op.setCodcargo(ler.nextInt());
        System.out.print("Digite o codigo de acesso:");
        op.setAcesso(ler.nextInt());
        op.setFechada(lerdata());
        retorno = op.getCodigo() + "\n" + op.getDescricao() + "\n" + op.getCodcargo()+ "\n" + op.getAcesso()+ "\n" + op.getFechada();
        return retorno;
    }
    
    public static Date lerdata(){
        System.out.print("Digite a data de fechamento(dd/MM/yyyy):");
        String dataRecebida = ler.next();
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
	Date dt = null;
        try {
            dt = df.parse(dataRecebida);
        } catch (ParseException ex) {
            System.out.println("Formato errado da data" + ex.getMessage());
        }
        System.out.println("");
        return dt;
    }
    public static String[] Serializar(String dados){
        int tam = 0;
        tam = dados.length()/97;
        if(dados.length()%97 > 1)
            tam++;
        int indice = 0;
        int indice2 = 97;
        String[] vetor = new String[tam];
        for(int i = 0; i < tam; i++){
            indice = i * 96;
            
            if(indice2 > dados.length()){
                indice2 = dados.length();
            }
            String aux = String.format("%03d",i+1)+ "\n" + dados.substring(indice, indice2);
            vetor[i]= aux;
            indice2 += 97;
        }
        return vetor;
    }
    public static int enviar(String dados, int porta){
        byte  sdados[] = new byte[100];
        byte  rdados[] = new byte[100];
        InetAddress ip = null;
        DatagramSocket s = null;
        try {
            ip = InetAddress.getByName("localhost");
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            s = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        sdados = dados.getBytes();
        DatagramPacket sPack = new DatagramPacket(sdados, sdados.length, ip, porta);
        try {
            s.send(sPack);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        Integer nporta= 0;
        if(porta == 2001){
            DatagramPacket re = new DatagramPacket(rdados, rdados.length);
            try {
                s.receive(re);
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            nporta = re.getPort();
            System.out.println(" Porta:" + nporta.toString());
        }
        s.close();
        return nporta;
    }
    
}
