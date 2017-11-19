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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ClienteTCP {
    
    public static Scanner ler = new Scanner(System.in);
    
    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    public static ArquivoLista enviar (Socket cliente, ArquivoLista arq, ObjectOutputStream saida, ObjectInputStream entrada) throws Exception{
        saida.writeObject(arq);
        saida.flush();
        
        return (ArquivoLista) entrada.readObject();
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
        Oportunidade op = new Oportunidade();
                
        while (true){
            System.out.println("\nTIPOS DE OPORTUNIDADES");
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
        
        ArquivoLista arquivoLista = new ArquivoLista();
        System.out.print("Digite a data de FECHAMENTO (dd/MM/yyyy): ");
        String dataRecebida = ler.next();
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");  
	Date dt = df.parse(dataRecebida);
        
        return dt;
    }
    
//    public static Cargo lerCargo() throws IOException, ParseException{
//        
//        Cargo cargo = new Cargo();
//        
//        System.out.print("Digite a DESCRIÇÃO do cargo para inserí-la: ");
//        cargo.setDescricao(in.readLine());
//        
//        cargo.setTipo(lerTipo());
//        
//        return cargo;
//    }
    
    public static Object lerOportunidade() throws IOException, ParseException{
        
        Oportunidade op = new Oportunidade();
        Cargo cargo = new Cargo();
        System.out.print("Digite o CÓDIGO da oportunidade para inserí-la: ");
        
        op.setCodigo(ler.nextInt());
        
        System.out.print("Digite a DESCRIÇÃO da oportunidade para inserí-la: ");
        op.setDescricao(in.readLine());
        
        System.out.print("Digite o CÓDIGO do cargo para inserí-lo: ");
        op.setCodcargo(ler.nextInt());
        
        op.setAcesso(lerAcesso());
        op.setCargo(cargo);
        cargo.setTipo(lerTipo());
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
        
        System.out.println("\nSe quiser alterar o campo digite \"S\" senão digite \"N\".\n");
        System.out.print("Digite o CÓDIGO da oportunidade a ser alterada: ");
        op.setCodigo(ler.nextInt());
        
        System.out.print("\nAlterar DESCRIÇÃO (S/N)? ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            op.setDescricao(Aux_Alterar("a descrição: "));
            teste = "n";
        }
        else op.setDescricao(null);
        
        System.out.print("\nAlterar CARGO (S/N)? ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            op.setCodcargo(Integer.parseInt(Aux_Alterar("o código do cargo: ")));
            teste = "n";
        }
        else op.setCodcargo(0);
        
        System.out.print("\nAlterar ACESSO (S/N)? ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            op.setAcesso(lerAcesso());
            teste = "n";
        }
        else op.setAcesso(0);
        
        System.out.print("\nAlterar DATA FECHAMENTO (S/N)? ");
        teste = ler.next();
        if(teste.equals("s") || teste.equals("S")){
            op.setFechada(lerFechada());
            teste = "n";
        }
        else op.setFechada(null);
                
        return op;
    }
    
    public static String Aux_Alterar(String str){
        String ret = new String();
        System.out.print("Digite " + str);
        try {
            ret = in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    
    public static Oportunidade listarOportunidades(){
        Oportunidade op = new Oportunidade(); 
        System.out.print("Digite o CÓDIGO do cargo para listar as oportunidades: ");
        op.setCodcargo(ler.nextInt());
        return op;
    }
    
    public static Cargo listarAbertas(){
        Cargo cargo = new Cargo(); 
        cargo.setTipo(lerTipo());
        return cargo;
        
    }
    
//    public static String lerRetorno(){
//        ArquivoLista arquivoLista = new ArquivoLista();
//        String ret = null;
//        
//        if(arquivoLista.getRet() == 0){
//            ret = arquivoLista.getRetorno();
//        }
//        else if(arquivoLista.getRet() == 1){
//            ret = "\nErro: "+ arquivoLista.getRetorno();
//        }
//        return ret;
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
                System.out.println("\n****************************");
                System.out.println("          OPERAÇÕES          ");
                System.out.println("****************************");
                System.out.println("[1] Adicionar");
                System.out.println("[2] Alterar");
                System.out.println("[3] Consultar");
                System.out.println("[4] Excluir");
                System.out.println("[5] Listar Oportunidades");
                System.out.println("[6] Listar Abertas");       
                System.out.println("[7] Sair");
                System.out.print("Digite a opção desejada: ");

                Integer operacao = ler.nextInt();
                Object dados = new Object();
                Oportunidade op = new Oportunidade();
                Cargo cargo = new Cargo();
                ArquivoLista arquivoLista =  new ArquivoLista();
                arquivoLista.setOperacao(operacao);
                List<Object> listaOportunidades = new ArrayList<Object>();
                String ret = null;
                
                switch(operacao){
                    case 1: //adicionar
                        dados = lerOportunidade();
                        listaOportunidades.add(dados);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        //ret = lerRetorno();
                        if(arquivoLista.getRet() == 0){
                            ret = arquivoLista.getRetorno();
                        }
                        else if(arquivoLista.getRet() == 1){
                            ret = "\nErro: "+ arquivoLista.getRetorno();
                        }
                        System.out.println(ret);
                        break;
                        
                    case 2: //alterar
                        dados = alterar();
                        listaOportunidades.add(dados);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        //ret = lerRetorno();
                        if(arquivoLista.getRet() == 0){
                            ret = arquivoLista.getRetorno();
                        }
                        else if(arquivoLista.getRet() == 1){
                            ret = "\nErro: "+ arquivoLista.getRetorno();
                        }
                        System.out.println(ret);
                        break;
                        
                    case 3: //consultar
                        dados = consultar();
                        listaOportunidades.add(dados);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        //ret = lerRetorno();
                        if(arquivoLista.getRet() == 0){
                            ret = arquivoLista.getRetorno();
                        }
                        else if(arquivoLista.getRet() == 1){
                            ret = "\nErro: "+ arquivoLista.getRetorno();
                        }
                        System.out.println(ret);
                        break;
                        
                    case 4: //excluir
                        dados = excluir();
                        listaOportunidades.add(dados);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
                        //ret = lerRetorno();
                        if(arquivoLista.getRet() == 0){
                            ret = arquivoLista.getRetorno();
                        }
                        else if(arquivoLista.getRet() == 1){
                            ret = "\nErro: "+ arquivoLista.getRetorno();
                        }
                        System.out.println(ret);
                        break;
                        
                    case 5: //listarOportunidades
                        dados = listarOportunidades();
                        listaOportunidades.add(dados);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada); 
                        //ret = lerRetorno();
                        if(arquivoLista.getRet() == 0){
                            ret = arquivoLista.getRetorno();
                            System.out.println("\nOportunidades do cargo: " + op.getCodcargo());
                            
                            if (arquivoLista.getObjetos().size() > 0){
                                System.out.println("..................................................................");
                                
                                for (Object oport : arquivoLista.getObjetos()){
                                    op = (Oportunidade) oport;

                                    System.out.print("\nCódigo: " + op.getCodigo());
                                    System.out.print("\nDescrição: " + op.getDescricao());
                                    System.out.print("\nAcesso: " + op.getAcesso());
                                    System.out.print("\nIngresso: " + op.getIngresso());
                                    System.out.print("\nFechada: " + op.getFechada());
                                    System.out.println("\n..................................................................");
                                }
                            }
                            else{
                                System.out.println("\nAinda não há oportunidades para este cargo!");
                            }   
                        }
                        else if(arquivoLista.getRet() == 1){
                            ret = "\nErro: "+ arquivoLista.getRetorno();
                        }
                        System.out.println(ret);
                        break;
                        
                    case 6: //listarAbertas
                        dados = listarAbertas();
                        listaOportunidades.add(dados);
                        arquivoLista.setObjetos(listaOportunidades);
                        arquivoLista = enviar(cliente, arquivoLista, saida, entrada); 
                        //ret = lerRetorno();
                        if(arquivoLista.getRet() == 0){
                            ret = arquivoLista.getRetorno();
                            System.out.println("\nOportunidades abertas do tipo: " + cargo.getTipo());
                            
                            if (arquivoLista.getObjetos().size() > 0){
                                System.out.println("..................................................................");
                                
                                for (Object aberta : arquivoLista.getObjetos()){
                                    cargo = (Cargo) aberta;

                                    System.out.print("\nCódigo: " + op.getCodigo());
                                    System.out.print("\nDescrição: " + op.getDescricao());
                                    System.out.print("\nAcesso: " + op.getAcesso());
                                    System.out.print("\nIngresso: " + op.getIngresso());
                                    System.out.print("\nFechada: " + op.getFechada());
                                    System.out.println("\n..................................................................");
                                }
                            }
                            else{
                                //mostrar tudo
                            }
                        }
                        else if(arquivoLista.getRet() == 1){
                            ret = "\nErro: "+ arquivoLista.getRetorno();
                        }
                        break;
                        
                    case 7:
                        System.out.println("Saindo...");
                        //arquivoLista = enviar(cliente, arquivoLista, saida, entrada);
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
