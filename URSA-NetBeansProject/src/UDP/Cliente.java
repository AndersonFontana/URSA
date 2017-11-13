/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import static UDP.Processar.*;
import dominio.Oportunidade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        DatagramSocket soc = null;
        try {
            soc = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String operacao = null;
        while(true){
            operacao = null;
            String dados = null;
            String[] vetor = null;
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
                dados = alterar();
            }
            else if(operacao.equals("-e")){
                dados = excluir();
            }
            else if(operacao.equals("-c")){
                String dadosOP = consulta(soc);
                mostrarOP(dadosOP);
                continue;
            }
            else if(operacao.equals("-q")){
                soc.close();
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
            int porta = enviar(operacao + "\n" + tamanho, 2001, soc);
            for(int i = 0; i < vetor.length; i++){
                enviar(vetor[i], porta, soc);
            }
            System.out.println(receber(soc));
            
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
        String retorno = new String();
        Oportunidade op = new Oportunidade();
        System.out.println("Adicionar oportunidade, preencha os campos abaixo.");
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
        //op.setFechada(lerdata());
        retorno = op.getCodigo() + "\n" + op.getDescricao() + "\n" + op.getCodcargo()+ "\n" + op.getAcesso()+ "\n" + lerdata();
        return retorno;
    }
    
    public static String consulta(DatagramSocket soc){
        Oportunidade op = new Oportunidade();
        String retorno = new String();
        String operacao = new String();
        operacao = String.format("%03d",0) + "\n" + "-c";
        int porta = enviar(operacao + "\n" + 1, 2001, soc);
        System.out.println("Consulta Oprtunidade");
        System.out.print("Digite o codigo da oportunidade: ");
        retorno = ler.next();
        retorno = String.format("%03d",1) + "\n" + retorno;
        enviar(retorno, porta, soc);
        String aux = receber(soc);
        aux = aux.split("\n")[1].trim();
        Integer tam = Integer.parseInt(aux);
        String Vetdados [] = new String[tam];
        for(int i = 0; i < tam; i++){
            Integer posicao = 0;
            String auxx = receber(soc);
            posicao = (Integer.parseInt(auxx.split("\n")[0].trim()))-1;
            auxx = auxx.split("\n", 2)[1];
            Vetdados[posicao] = auxx;
        }
        String dadosOP = concat(Vetdados);
        return dadosOP;
    }
    
    public static String alterar(){
        String retorno = new String();
        String dt = new String();
        Oportunidade op = new Oportunidade();
        String teste = "n";
        System.out.println("Alterar oportunidade, digite o codigo da oportunidade a ser alterada.\n"
                           + "Se quiser alterar o campo digite s senao digite n");
        System.out.print("Digite o Codigo da oportunidade a ser alterada:");
        op.setCodigo(ler.nextInt());
        System.out.print("Alterar Descricao(s/n): ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            op.setDescricao(Aux_Alterar("a Descricao"));
            teste = "n";
        }
        else{
            op.setDescricao("");
        }
        System.out.print("Alterar Cargo(s/n): ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            op.setCodcargo(Integer.parseInt(Aux_Alterar("o codigo do cardo")));
            teste = "n";
        }
        else{
            op.setCodcargo(0);
        }
        System.out.print("Alterar Acesso(s/n): ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            op.setAcesso(Integer.parseInt(Aux_Alterar("o codigo de acesso")));
            teste = "n";
        }
        else{
            op.setAcesso(0);
        }
        System.out.print("Alterar data fechamento(s/n): ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            dt = lerdata();
            teste = "n";
        }
        else{
            dt= null;
        }
        retorno = op.getCodigo() + "\n" + op.getDescricao() + "\n" + op.getCodcargo()+ "\n" + op.getAcesso()+ "\n" + dt;
        return retorno;
    }
    public static String excluir(){
        String retorno = new String();
        System.out.println("Excluir Oportunidade");
        System.out.print("Digite o codigo da oportunidade a ser excluida: ");
        retorno = ler.next();
        return retorno;
    }
  
    public static String lerdata(){
        System.out.print("Digite a data de fechamento(dd/MM/yyyy):");
        String dataRecebida = ler.next();
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
	Date dt = new Date();
        try {
            dt = df.parse(dataRecebida);
        } catch (ParseException ex) {
            System.out.println("Formato errado da data" + ex.getMessage());
        }
        System.out.println("");
        return dataRecebida;
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
    public static int enviar(String dados, int porta, DatagramSocket s){
        byte  sdados[] = new byte[100];
        byte  rdados[] = new byte[100];
        InetAddress ip = null;
        try {
            ip = InetAddress.getByName("localhost");
        } catch (UnknownHostException ex) {
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
            //System.out.println(" Porta:" + nporta.toString());
        }
        return nporta;
    }
    public static String receber(DatagramSocket soc){
        byte  rdados[] = new byte[100];
        DatagramPacket re = new DatagramPacket(rdados, rdados.length);
        try {
            soc.receive(re);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        String dados = new String(re.getData());
        return dados;
    }
    public static String Aux_Alterar(String str){
        String ret = new String();
        System.out.print("Digite " + str + ": ");
        try {
            ret = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    public static void mostrarOP(String dados){
        System.out.println("Codigo: " + dados.split("\n")[0].trim());
        System.out.println("Descricao: " + dados.split("\n")[1].trim());
        System.out.println("Codigo Cargo: " + dados.split("\n")[2].trim());
        System.out.println("Codigo Acesso: " + dados.split("\n")[3].trim());
        System.out.println("Data Ingresso: " + dados.split("\n")[4].trim());
        System.out.println("Data Fechamento: " + dados.split("\n")[5].trim());
    }
    
  
    
}
