/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import BANCO.DaoBanco;
import UDP.Cliente;
import dominio.Cargo;
import dominio.Oportunidade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniela
 */
public class ClienteTCP {
    
    public static void enviarReceber(Oportunidade dados){
        
        Scanner ler = new Scanner(System.in);
        
        int port = 1972;
        String host = new String("localhost");
        try{
            Socket cliente = new Socket(host, port);
            System.out.println("Cliente conectou com servidor na porta: "+port);

            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
                       
            saida.writeObject(dados);
            saida.flush();
            System.out.println("Conexão encerrada!");   
        }
        catch(Exception e){
            System.out.println("Erro: "+e.getMessage());
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
        }
    }
       
    public static Integer lerAcesso(){
        Scanner ler = new Scanner(System.in);
                
        while (true)
        {
            System.out.println("\nACESSOS");
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
            System.out.println("\nTIPOS DE OPORTUNIDADES:");
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
    
    public static Oportunidade lerOportunidade() throws IOException{
        Scanner ler = new Scanner(System.in); 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //String retorno = null;
        
        Oportunidade op = new Oportunidade();
        System.out.print("Digite o CÓDIGO da oportunidade: ");
        op.setCodigo(ler.nextInt());
        
        System.out.print("Digite a DESCRIÇÃO da oportunidade: ");
        op.setDescricao(in.readLine());
        
        op.setAcesso(lerAcesso());  
        
        //ingresso: timestamp
        //fechada: timestamp
        
        //retorno = op.getCodigo() + "\n" + op.getDescricao() + "\n" + op.getAcesso();
        return op;
           
    }
    
    public static Cargo lerCargo() throws IOException{
        Scanner ler = new Scanner(System.in); 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        Cargo cargo = new Cargo(); 
        
        System.out.println("Digite o CÓDIGO do cargo: ");
        cargo.setCodcargo(ler.nextInt());
        
        System.out.print("Digite a DESCRIÇÃO do cargo: ");
        cargo.setDescricao(in.readLine());
        
        cargo.setTipo(lerTipo());  
        
        return cargo;
    }
    
    public static void main(String[] args) throws Exception{
        
        Scanner ler = new Scanner(System.in);
        
//        try{
//            int port = 1972;
//            String host = new String("localhost");
//
//            Socket cliente = new Socket(host, port);
//            System.out.println("Cliente conectou com servidor na porta: "+port);
//
//            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());

            while(true){
                System.out.println("\n*******************");
                System.out.println("     OPERAÇÕES   ");
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
                Oportunidade op = new Oportunidade();
                DaoBanco dao = new DaoBanco();
                Cargo cargo = new Cargo();
                List<Oportunidade> listaOportunidades = new ArrayList();
                
                switch(operacao){
                    case 1: //adicionar
                        op = lerOportunidade();
                        listaOportunidades.add(op);
                        //enviarReceber(op);
                        //lista.add(op);
                        System.out.println(dao.adicionar(op));
                        break;
                        
                    case 2: //alterar
                        op = lerOportunidade();
                        listaOportunidades.add(op);
                        //enviarReceber(op);
                        //lista.add(op);
                        System.out.println(dao.alterar(op));
                        break;
                        
                    case 3: //consultar
                        op = lerOportunidade();
                        listaOportunidades.add(op);
                        //enviarReceber(op);
                        //lista.add(op);
                        System.out.println(dao.consultar(op));
                        break;
                        
                    case 4: //excluir
                        op = lerOportunidade();
                        listaOportunidades.add(op);
                        //enviarReceber(op);
                        //lista.add(op);
                        System.out.println(dao.excluir(op));
                        break;
                        
                    case 5: //listarOportunidades
                        op = lerOportunidade();
                        //enviarReceber(op);
                        //lista.add(op);
                        listaOportunidades = dao.listaOportunidades(1);
                        //System.out.println(dao.listaOportunidades(codCargo));                     
                        break;
                        
                    case 6: //listarAbertas
                        cargo.setTipo(lerTipo());
                        //enviarReceber(op);
                        //lista.add(cargo);
                        listaOportunidades = dao.listaAbertas(7);
                        //System.out.println(dao.listaAbertas(tipo));            
                        break;
                        
                    case 7:
                        System.out.println("Sair...");
                        break;
                }
            }
//        }
//        catch(Exception e){
//            System.out.println("Erro: "+e.getMessage());  
//        }
//        finally{
//                
//        }
    }    
}
