/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import dominio.Cargo;
import dominio.Oportunidade;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniela
 */
public class ClienteTCP {
    
    public static Scanner ler = new Scanner(System.in);
    
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    public static Arquivo enviar (Socket cliente, Arquivo arq, ObjectOutputStream saida, ObjectInputStream entrada) throws Exception{
        saida.writeObject(arq);
        saida.flush();
        
        return (Arquivo) entrada.readObject();
    }
   
    public static Integer lerAcesso(){
                
        while (true){
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
        
            return acesso;
        }
    }
    
    public static Integer lerTipo(){
                
        while (true){
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
            
            return tipo;
        }
    }
    
    public static Timestamp lerFechada() throws ParseException{
        
        Arquivo arquivoLista = new Arquivo();
        System.out.print("Digite a data de FECHADA (dd/MM/yyyy): ");
        String dataFechada = ler.nextLine();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");    
	Date sdt = new Date();
        if (!dataFechada.equals("")){
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(dataFechada));
            arquivoLista.setData(cal);
        }
        return Timestamp.valueOf(dataFechada);
    }
    
    public static Timestamp lerIngresso() throws ParseException{ 
        
        Arquivo arquivoLista = new Arquivo();
        
        System.out.print("Digite a data de INGRESSO (dd/MM/yyyy): ");
        String dataIngresso = ler.nextLine();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  
	if (!dataIngresso.equals("")){
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(dataIngresso));
            arquivoLista.setData(cal);
        }        
        return Timestamp.valueOf(dataIngresso);
    }
        
    public static Oportunidade lerOportunidade() throws IOException, ParseException{
        
        Oportunidade op = new Oportunidade();
        Cargo cargo = new Cargo();
        
        System.out.print("Digite o CÓDIGO da oportunidade para inserí-lo: ");
        op.setCodigo(ler.nextInt());
        
        System.out.print("Digite a DESCRIÇÃO da oportunidade para inserí-la: ");
        op.setDescricao(in.readLine());
        
        System.out.print("Digite o CÓDIGO do cargo para inserí-lo: ");
        op.setCodcargo(ler.nextInt());
        
        op.setAcesso(lerAcesso());
        
        op.setFechada(lerFechada());
        
        return op;  
    }
    
     public static Cargo lerCargo() throws IOException{
        Cargo cargo = new Cargo(); 
        
        System.out.print("Digite o CÓDIGO do cargo: ");
        cargo.setCodcargo(ler.nextInt());
        
        System.out.print("Digite a DESCRIÇÃO do cargo: ");
        cargo.setDescricao(in.readLine());
        
        cargo.setTipo(lerTipo());  
        
        return cargo;
    }
    
    public static Oportunidade excluir(){
        Oportunidade op = new Oportunidade();
        System.out.print("Digite o CÓDIGO da oportunidade para excluí-la: ");
        op.setCodigo(ler.nextInt());
        return op;
    }
    
    public static Oportunidade consultar(){
        Oportunidade op = new Oportunidade();
        System.out.print("Digite o CÓDIGO da oportunidade para consultá-la: ");
        op.setCodigo(ler.nextInt());
        return op;        
    }
    
    public static Oportunidade alterar(){
        Oportunidade op = new Oportunidade();
        System.out.print("Digite o CÓDIGO da oportunidade para alterá-la: ");
        op.setCodigo(ler.nextInt());
        return op;        
    }
    
    public static Cargo listarOportunidades(){
        Cargo cargo = new Cargo(); 
        System.out.print("Digite o CÓDIGO do cargo para listar as oportunidades: ");
        cargo.setCodcargo(ler.nextInt());
        return cargo;
    }
    
    public static Oportunidade listarAbertas(){
        Oportunidade op = new Oportunidade(); 
        System.out.print("Digite o TIPO da oportunidade para listar as em aberto: ");
        op.setAcesso(lerAcesso());
        return op;
        
    }
    
    public static String retorno(){
        Arquivo arquivoLista =  new Arquivo();
        String ret = null;
        if (arquivoLista.getCodigo() == 0){
            ret = arquivoLista.getRetorno();
        }
        else if (arquivoLista.getCodigo() == 1){
            ret = "Erro: " + arquivoLista.getRetorno();
        }
        return ret;
    }
    
//    public static void enviarReceber(Oportunidade dados){
//        
//        Scanner ler = new Scanner(System.in);
//        
//        int port = 1972;
//        String host = new String("localhost");
//        try{
//            Socket cliente = new Socket(host, port);
//            System.out.println("Cliente conectou com servidor na porta: "+port);
//
//            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
//                       
//            saida.writeObject(dados);
//            saida.flush();
//            System.out.println("Conexão encerrada!");   
//        }
//        catch(Exception e){
//            System.out.println("Erro: "+e.getMessage());
//            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
    
    public static void main(String[] args) throws Exception{
        
        try{
            int port = 1972;
            String host = new String("localhost");

            Socket cliente = new Socket(host, port);
            
            ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream()); 
            System.out.println("Cliente conectou com servidor na porta: "+port);

            while(true){
                System.out.println("\n*******************");
                System.out.println("     OPERAÇÕES      ");
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
                Cargo cargo = new Cargo();
                String ret = null;
                Arquivo arquivoLista =  new Arquivo();
                arquivoLista.setOperacao(operacao);
                List<Object> listaOportunidades = new ArrayList();
                
                switch(operacao){
                    case 1: //adicionar
                        op = lerOportunidade();
                        listaOportunidades.add(op);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        ret = retorno();
                        System.out.println(ret);
                        break;
                        
                    case 2: //alterar
                        op = alterar();
                        listaOportunidades.add(op);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        ret = retorno();
                        System.out.println(ret);
                        break;
                        
                    case 3: //consultar
                        op = consultar();
                        listaOportunidades.add(op);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        ret = retorno();
                        System.out.println(ret);
                        break;
                        
                    case 4: //excluir
                        op = excluir();
                        listaOportunidades.add(op);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        ret = retorno();
                        System.out.println(ret);
                        break;
                        
                    case 5: //listarOportunidades
                        cargo = listarOportunidades();
                        listaOportunidades.add(op);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);    
                        ret = retorno();
                        System.out.println(ret);
                        break;
                        
                    case 6: //listarAbertas
                        op = listarAbertas();
                        listaOportunidades.add(op);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);  
                        ret = retorno();
                        System.out.println(ret);
                        break;
                        
                    case 7:
                        System.out.println("Sair...");
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        cliente.close();
                        System.exit(0);
                        break;
                }
            }
        }
        catch(SocketException ex){
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro: "+ex.getMessage());  
        }
        finally{
                
        }
    }    
}
