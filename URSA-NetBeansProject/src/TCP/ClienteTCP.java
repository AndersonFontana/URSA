/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import dominio.Cargo;
import dominio.Oportunidade;
import java.io.ObjectInputStream;
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
    
    public static void enviarReceber(String dados){
        
        Scanner ler = new Scanner(System.in);
        
        int port = 1972;
        String host = new String("localhost");
        
        try{
            Socket cliente = new Socket(host, port);
            System.out.println("Cliente conectou com servidor na porta: "+port);

            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            
        }
        catch(Exception e){
            System.out.println("Erro: "+e.getMessage());
        }
    }
       
    public static Integer lerAcesso(){
        Scanner ler = new Scanner(System.in);
                
        while (true)
        {
            System.out.println("ACESSOS");
            System.out.println("[1] Somente Agronomia");
            System.out.println("[2] Somente Engenharia Agronomica");
            System.out.println("[3] Somente Agronegocio");
            System.out.println("[4] Agronomia ou Engenharia Agronomica");
            System.out.println("[5] Agronomia ou Agronegocio");
            System.out.println("[6] Engenharia Agronomica ou Agronegocio");
            System.out.println("[7] Todos os cursos");
            System.out.print("Digite o número de acesso: ");
            Integer acesso = Integer.parseInt(ler.nextLine());
        
            if (acesso > 7 || acesso < 1){
                System.out.println("\n************************");
                System.out.println("Erro: Tipo inválido");
                System.out.println("************************\n");
                continue;
            }
            else{
                return acesso;
            }
        }
    }
    
    public static Integer lerTipo(){
        Scanner ler = new Scanner(System.in);
                
        while (true)
        {
            System.out.println("TIPOS DE OPORTUNIDADES:");
            System.out.println("[1] Emprego formal");
            System.out.println("[2] Estágio até 20h/semana");
            System.out.println("[3] Estágio acima de 20h/semana");
            System.out.println("[4] Estágio voluntário");
            System.out.println("[5] Bolsa de pesquisa");
            System.out.println("[6] Bolsa de extensão");
            System.out.println("[7] Bolsa de graduação");
            System.out.print("Digite o tipo da oportunidade: ");
            Integer tipo = Integer.parseInt(ler.nextLine());

            if (tipo > 7 || tipo < 1){
                System.out.println("\n************************");
                System.out.println("Erro: Tipo inválido");
                System.out.println("************************\n");
                continue;
            }
            else{
                return tipo;
            }
        }
    }
    
    
    public static Oportunidade lerOportunidade(){
        
        Scanner ler = new Scanner(System.in);        
        Oportunidade oportunidade = new Oportunidade();      
        oportunidade.setAcesso(lerAcesso());          
        return oportunidade;        
    }
    
    public static Cargo lerCargo(){
        
        Scanner ler = new Scanner(System.in);        
        Cargo cargo = new Cargo();        
        cargo.setTipo(lerTipo());      
        return cargo;
    }
    
    
    public static ArquivoBD comunicar (Socket cliente,
                                       ArquivoBD arquivo,
                                       ObjectOutputStream saida,
                                       ObjectInputStream entrada) throws Exception{
        saida.writeObject(arquivo);
        saida.flush();
        System.out.println("Conexão encerrada!");
        
        return (ArquivoBD) entrada.readObject();
    }
    
//    public static String adicionar() throws IOException{
//                
//        Oportunidade oportunidade = new Oportunidade();
//        String retornaOp = null;
//        oportunidade = lerOportunidade();
//        ArquivoBD arquivo = new ArquivoBD();
//        //arquivo.setOpe(operacao);
//        List<Object> lista = new ArrayList<Object>();
//        
//        lista.add(oportunidade);
//        
//        arquivo.setObjetos(lista);
//        //arquivo = comunicar(cliente, arquivo, saida, entrada);
//        
//        retornaOp = oportunidade.getCodigo() + "\n" 
//                  + oportunidade.getCodcargo()+ "\n"
//                  + oportunidade.getCargo()+ "\n"
//                  + oportunidade.getDescricao() + "\n"
//                  + oportunidade.getAcesso()+ "\n" 
//                  + oportunidade.getIngresso()+ "\n"
//                  + oportunidade.getFechada();
//        
//        return retornaOp;
//    }    
    
//    public static void consultar(){
//        
//    }
//    
//    public static void excluir(){
//        
//    }
//    
//    public static void alterar(){
//        
//    }
//      
//    public static void listarOportunidades(){
//        
//    }
    
//    public static void listarAbertas(){
//        
//    }
        
    public static void main(String[] args) throws Exception{
        
        try{
                Scanner ler = new Scanner(System.in);

                int port = 1972;
                String host = new String("localhost");

                Socket cliente = new Socket(host, port);
                System.out.println("Cliente conectou com servidor na porta: "+port);

                ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());

              
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

                Oportunidade oportunidade = new Oportunidade();
                Cargo cargo = new Cargo();
                String retornaOp = null;
                ArquivoBD arquivo = new ArquivoBD();
                List<Object> lista = new ArrayList<Object>();
                arquivo.setOpe(operacao);


                switch(operacao){
                    case 1:
                        //adicionar();
                        oportunidade = lerOportunidade();
                        lista.add(oportunidade);
                        arquivo.setObjetos(lista);
                        arquivo = comunicar(cliente, arquivo, saida, entrada);
                        break;
                    case 2:
                        //alterar();
                        oportunidade = lerOportunidade();
                        lista.add(oportunidade);
                        arquivo.setObjetos(lista);
                        arquivo = comunicar(cliente, arquivo, saida, entrada);
                        break;
                    case 3:
                        //consultar();
                        oportunidade = lerOportunidade();
                        lista.add(oportunidade);
                        arquivo.setObjetos(lista);
                        arquivo = comunicar(cliente, arquivo, saida, entrada);
                        break;
                    case 4:
                        //excluir();
                        oportunidade = lerOportunidade();
                        lista.add(oportunidade);
                        arquivo.setObjetos(lista);
                        arquivo = comunicar(cliente, arquivo, saida, entrada);
                        break;
                    case 5:
                        //listarOportunidades();
                        oportunidade = lerOportunidade();
                        lista.add(oportunidade);
                        arquivo.setObjetos(lista);
                        arquivo = comunicar(cliente, arquivo, saida, entrada);
                        break;
                    case 6:
                        //listarAbertas();
                        cargo = lerCargo();
                        lista.add(cargo);
                        arquivo.setObjetos(lista);
                        arquivo = comunicar(cliente, arquivo, saida, entrada);
                        break;
                    case 7:
                        System.out.println("Sair...");
                        break;

                }
            }
            
        }
        catch(Exception e){
            System.out.println("Erro: "+e.getMessage());  
        }
        finally{
                
        }
    }    
}
