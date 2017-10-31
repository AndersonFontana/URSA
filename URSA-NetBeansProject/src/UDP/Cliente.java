/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import dominio.Oportunidade;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author João Pedro
 */

public class Cliente {
    public static Scanner ler = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        
        System.out.println("Para ver os comandos digite (-help)");
        Integer porta = 2001;
        InetAddress ip = InetAddress.getByName("localhost");
        DatagramSocket s = new DatagramSocket(porta);
        String operacao = null;
        while(true){
            byte  sdados[] = new byte[100];
            operacao = null;
            String dados = null;
            System.out.print("Insira a Operação: ");
            operacao = ler.next();
            if(operacao.equals("-help")){
                help();
            }
            else if(operacao.equals("-i")){
                dados = operacao + "-" + incluir();
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
            }
            sdados = dados.getBytes();
            DatagramPacket sPack = new DatagramPacket(sdados, sdados.length, ip, porta);
            
        }
    }
    
    public static void help(){
        System.out.println("-i: inclui oportunidade");
        System.out.println("-a: altera oportunidade");
        System.out.println("-e: exclui oportunidade");
        System.out.println("-c: consulta oportunidade");
        System.out.println("-q: sair");
    }
    public static String incluir() throws ParseException{
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
        op.setFechada((Timestamp) lerdata());        
        System.out.print("Digite o Codigo:");
        retorno = op.getCodigo() + "-" + op.getDescricao() + "-" + op.getCodcargo()+ "-" + op.getCodcargo() + "-" + op.getFechada();
        return retorno;
    }
    
    public static Date lerdata() throws ParseException{
        System.out.println("Digite a data de fechamento(dd/MM/yyyy):");
        String dataRecebida = ler.nextLine();
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
	Date dt = df.parse(dataRecebida);
        return dt;
    }
    
}
