/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import dominio.Oportunidade;
import java.io.IOException;
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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException{
        // TODO code application logic here
        
        
        System.out.println("Para ver os comandos digite (-help)");
        
        String operacao = null;
        while(true){
            operacao = null;
            String dados = null;
            System.out.print("Insira a Operação: ");
            operacao = ler.next();
            if(operacao.equals("-help")){
                help();
                continue;
            }
            else if(operacao.equals("-i")){
                dados = operacao + "\n" + incluir();
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
            Serializar(dados);
            
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
        op.setDescricao(ler.next());
        System.out.print("Digite o codigo do cargo:");
        op.setCodcargo(ler.nextInt());
        System.out.print("Digite o codigo de acesso:");
        op.setAcesso(ler.nextInt());
        op.setFechada(lerdata());
        retorno = op.getCodigo() + "\n" + op.getDescricao() + "\n" + op.getCodcargo()+ "\n" + op.getCodcargo() + "\n" + op.getFechada();
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
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("");
        return dt;
    }
    public static void Serializar(String dados){
        int tam = 0;
        tam = dados.length()/97;
        if(dados.length()%97 > 1)
            tam++;
        int indice = 0;
        int indice2 = 97;
        for(int i = 0; i < tam; i++){
            indice = i * 97;
            if(indice2 > dados.length()){
                indice2 = dados.length();
            }
            String aux = String.format("%03d",i)+ "\n" + dados.substring(indice, indice2);
        }
    }
    
}
