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
import java.text.DateFormat;
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
            Integer acesso = ler.nextInt();
        
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
            Integer tipo = ler.nextInt();
            
            return tipo;
        }
    }
    
    public static Date lerFechada() throws ParseException{
        
        Arquivo arquivoLista = new Arquivo();
        System.out.print("Digite a data de FECHAMENTO (dd/MM/yyyy): ");
        String dataRecebida = ler.next();
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
	Date dt = df.parse(dataRecebida);
        
        return dt;
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
    
    public static Oportunidade alterar() throws ParseException{

        Oportunidade op = new Oportunidade();
        String teste = "n";
        System.out.println("Alterar oportunidade, digite o CÓDIGO da oportunidade a ser alterada.\n"
                           + "Se quiser alterar o campo digite S senão digite N");
        System.out.print("Digite o CÓDIGO da oportunidade a ser alterada: ");
        op.setCodigo(ler.nextInt());
        System.out.print("Alterar DESCRIÇÃO (S/N)? ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            op.setDescricao(Aux_Alterar("a descrição"));
            teste = "n";
        }
        else{
            op.setDescricao(null);
        }
        System.out.print("Alterar CARGO (S/N)? ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            op.setCodcargo(Integer.parseInt(Aux_Alterar("o código do cargo")));
            teste = "n";
        }
        else{
            op.setCodcargo(0);
        }
        System.out.print("Alterar ACESSO (S/N)? ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            op.setAcesso(Integer.parseInt(Aux_Alterar("o código do acesso")));
            teste = "n";
        }
        else{
            op.setAcesso(0);
        }
        System.out.print("Alterar DATA FECHAMENTO (S/N)? ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            op.setFechada(lerFechada());
            teste = "n";
        }
        else{
            op.setFechada(null);
        }
        
        return op;
    }
    
    public static String Aux_Alterar(String str){
        String ret = new String();
        System.out.print("Digite " + str + ": ");
        try {
            ret = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public static Cargo listarOportunidades(){
        Cargo cargo = new Cargo(); 
        System.out.print("Digite o CÓDIGO do cargo para listar as oportunidades: ");
        cargo.setCodcargo(ler.nextInt());
        return cargo;
    }
    
    public static Oportunidade listarAbertas(){
        Oportunidade op = new Oportunidade(); 
        Cargo cargo = new Cargo(); 
        //System.out.print("Digite o TIPO da oportunidade para listar as em aberto: ");
        cargo.setTipo(lerTipo());
        return op;
        
    }
    
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

                Integer operacao = ler.nextInt();
                Oportunidade op = new Oportunidade();
                Cargo cargo = new Cargo();
                Arquivo arquivoLista =  new Arquivo();
                arquivoLista.setOperacao(operacao);
                List<Oportunidade> listaOportunidades = new ArrayList();
                
                switch(operacao){
                    case 1: //adicionar
                        op = lerOportunidade();
                        listaOportunidades.add(op);
                        arquivoLista.setOportunidades(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        break;
                        
                    case 2: //alterar
                        op = alterar();
                        listaOportunidades.add(op);
                        arquivoLista.setOportunidades(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        break;
                        
                    case 3: //consultar
                        op = consultar();
                        listaOportunidades.add(op);
                        arquivoLista.setOportunidades(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        break;
                        
                    case 4: //excluir
                        op = excluir();
                        listaOportunidades.add(op);
                        arquivoLista.setOportunidades(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        break;
                        
                    case 5: //listarOportunidades
                        cargo = listarOportunidades();
                        listaOportunidades.add(op);
                        arquivoLista.setOportunidades(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);    
                        break;
                        
                    case 6: //listarAbertas
                        op = listarAbertas();
                        listaOportunidades.add(op);
                        arquivoLista.setOportunidades(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);  
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
