/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author João Pedro
 */

public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        Scanner ler = new Scanner(System.in);
        
        System.out.println("Para ver os comandos digite (-help)");
        Integer porta = 2001;
        InetAddress ip = InetAddress.getByName("localhost");
        DatagramSocket s = new DatagramSocket(porta);
        String operacao = null;
        while(true){
            operacao = null;
            System.out.print("Insira a Operação: ");
            operacao = ler.next();
            if(operacao.equals("-help")){
                help();
            }
            else if(operacao.equals("-i")){
                incluir();
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
        Scanner ler = new Scanner(System.in);
        String oportunidade= null;
        System.out.print("Digite o Codigo:");
        oportunidade = ler.next();
        System.out.print("Digite o Codigo:");
        oportunidade = ler.next();
        System.out.print("Digite o Codigo:");
        oportunidade = ler.next();
        System.out.print("Digite o Codigo:");
        oportunidade = ler.next();
        return oportunidade;
    }
    
}
