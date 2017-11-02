/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import static UDP.Cliente.in;
import static UDP.Cliente.ler;
import dominio.Oportunidade;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Daniela
 */
public class ClienteTCP {
    
    public static void main(String[] args) throws Exception{
        
        Scanner ler = new Scanner(System.in);
        
        while(true){
            System.out.println("*******************");
            System.out.println("     OPERAÇÕES     ");
            System.out.println("*******************");
            System.out.println("[1] Adicionar");
            System.out.println("[2] Alterar");
            System.out.println("[3] Consultar");
            System.out.println("[4] Excluir");
            System.out.println("[5] Listar Oportunidades");
            System.out.println("[6] Listar Abertas");       
            System.out.println("[7] Sair");
            System.out.print("Digite a sua opção desejada: ");

            Integer operacao = Integer.parseInt(ler.nextLine());
            
            switch(operacao){
                case 1:
                    adicionar();
                    break;
                case 2:
                    alterar();
                    break;
                case 3:
                    consultar();
                    break;
                case 4:
                    excluir();
                    break;
                case 5:
                    listarOportunidades();
                    break;
                case 6:
                    listarAbertas();
                    break;
                case 7:
                    System.out.println("Sair...");
                    break;
                    
            }
           
        }
    }
           
    public static String adicionar() throws IOException{
        Oportunidade oportunidade = new Oportunidade();
        String retornaOp = null;
        
        List<Object> lista = new ArrayList<Object>();
        lista.add(oportunidade);
        
        System.out.print("Digite o CÓDIGO:");
        oportunidade.setCodigo(ler.nextInt());
                
        System.out.print("Digite o CÓDIGO DO CARGO:");
        oportunidade.setCodcargo(ler.nextInt());
               
        System.out.print("Digite a DESCRIÇÃO:");
        oportunidade.setDescricao(in.readLine());
        
        System.out.print("Digite o ACESSO:");
        oportunidade.setAcesso(ler.nextInt());
        
        
        retornaOp = oportunidade.getCodigo() + "\n" 
                  + oportunidade.getCodcargo()+ "\n"
                  + oportunidade.getCargo()+ "\n"
                  + oportunidade.getDescricao() + "\n"
                  + oportunidade.getAcesso()+ "\n" 
                  + oportunidade.getIngresso()+ "\n"
                  + oportunidade.getFechada();
        
        return retornaOp;
    }    
    
    public static void consultar(){
        
    }
    
    public static void excluir(){
        
    }
    
    public static void alterar(){
        
    }
      
    public static void listarOportunidades(){
        
    }
    
    public static void listarAbertas(){
        
    }
        
        
    public static void enviarReceber(String dados){
        int port = 1972;
        String host = new String("localhost");
        String msg = new String("Hello World!");
        
        try{
            Socket cliente = new Socket(host, port);
            System.out.println("Cliente conectou com servidor na porta: "+port);

            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            
            saida.writeObject(msg);
            saida.flush();
            saida.close();
            System.out.println("Conexão encerrada!");
        }
        catch(Exception e){
            System.out.println("Erro: "+e.getMessage());
        }
    }
    
}
