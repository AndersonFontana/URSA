/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import BANCO.DaoBanco;
import dominio.Cargo;
import dominio.Oportunidade;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ThreadServidorTCP extends Thread{

    /**
     * @param args the command line arguments
     */
    
    private Socket cliente;
    
    public ThreadServidorTCP(Socket cliente){
        this.cliente = cliente;
    }

    public Socket getCliente(){
        return cliente;
    }

    public void setCliente(Socket cliente){
        this.cliente = cliente;
    }
          
    public static Boolean inserir(Integer codigo, Integer codcargo, String descricao, Integer acesso, Date fechada) throws Exception {
        try{
            Oportunidade op = new Oportunidade(codigo, codcargo, descricao, acesso, fechada);
            DaoBanco dao = new DaoBanco();
            dao.adicionar(op);
            logInfo("Inserida oportunidade " + op.getCodigo());
            return true;
        }catch (Exception ex){
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            logInfo(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        
    }
    
    public static Boolean alterar(Integer codigo, Integer codcargo, String descricao, Integer acesso, Date fechada) throws Exception {
        try{
            DaoBanco dao = new DaoBanco();
            Oportunidade op = new Oportunidade(codigo, codcargo, descricao, acesso, fechada);
            op.setCodigo(codigo);
            op.setAcesso(acesso);
            op.setCodcargo(codcargo);
            op.setDescricao(descricao);
            op.setFechada(fechada);
            dao.alterar(op);
            logInfo("Alterada oportunidade " + op.getCodigo());
            return true;
        }catch (Exception ex){
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            logInfo(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }   

    public static Oportunidade consultar(Integer Codigo) throws Exception{
        try{
            DaoBanco dao = new DaoBanco();
            Oportunidade op = dao.consultar(Codigo);
            logInfo("Consultada oportunidade " + op.getCodigo());
            return op;
        }catch (Exception ex){
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            logInfo(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    public static Boolean excluir(Integer Codigo) throws Exception{
        try{
            DaoBanco dao = new DaoBanco();
            dao.excluir(Codigo);
            logInfo("Excluida oportunidade " + Codigo.toString());
            return true;
        }catch (Exception ex){
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            logInfo(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }

    public static List<Oportunidade> listaOportunidades(Integer Codcargo) throws Exception{
        try{
            DaoBanco dao = new DaoBanco();
            List<Oportunidade> listOp = new ArrayList<Oportunidade>();
            listOp = dao.listaOportunidades(Codcargo);
            
            for (Oportunidade op : listOp){
                op.setCodcargo(Codcargo);
            }
            logInfo("Consultada lista de oportunidade do cargo " + Codcargo.toString());
            return listOp;
        }catch (Exception ex){
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            logInfo(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    public static List<Cargo> listaAbertas(Integer tipo) throws Exception{
        try{
            DaoBanco dao = new DaoBanco();
            List<Cargo> listCargo = new ArrayList<Cargo>();
            //listCargo.add(dao.consultar(tipo));
            dao.listaAbertas(tipo);

            for (Cargo cargo : listCargo){
                cargo.setTipo(tipo);
            }
            logInfo("Consultada lista de oportunidade abertas do tipo " + tipo.toString());
            return listCargo;
        }catch (Exception ex){
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            logInfo(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
    }
    
    public static void logInfo(String log) {
	ServidorTCP.LOGGER.info(log);
    }

    public void run()
    {
        ObjectInputStream entrada = null;
        ObjectOutputStream saida = null;
        
        try{
            entrada = new ObjectInputStream(cliente.getInputStream());
            saida = new ObjectOutputStream(cliente.getOutputStream());
            
            while(true){
                Object obj = entrada.readObject();
                ArquivoLista arquivoLista = (ArquivoLista) obj;

                
                
                DaoBanco dao = new DaoBanco();
                
                List<Object> listaRetorno = new ArrayList<Object>();

                switch (arquivoLista.getOperacao()){
                    case 1: // Adicionar oportunidade
                        try{ 
                            Oportunidade op = (Oportunidade) arquivoLista.getObjetos().get(0);
                            Integer codcargo = op.getCodcargo();
                            String descricao = op.getDescricao();
                            Integer codigo = op.getCodigo();
                            Integer acesso = op.getAcesso();
                            Date fechada = op.getFechada();
                        
                            inserir(codigo, codcargo, descricao, acesso, fechada);
                            arquivoLista.setRetorno
                                    ("\n..................................................................\n"
                                    +"         ---> Oportunidade inserida com sucesso! <---" 
                                    +"\nCódigo: "+ op.getCodigo()
                                    +"\n..................................................................");
                            arquivoLista.setRet(0);
                        }catch (Exception ex){
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;

                    case 2: //Alterar oportunidade
                        try{
                            Oportunidade op = (Oportunidade) arquivoLista.getObjetos().get(0);
                            Integer codcargo = op.getCodcargo();
                            String descricao = op.getDescricao();
                            Integer codigo = op.getCodigo();
                            Integer acesso = op.getAcesso();
                            Date fechada = op.getFechada();
                            alterar(codigo, codcargo, descricao, acesso, fechada);
                            arquivoLista.setRetorno
                                    ("\n..................................................................\n"
                                    +"         ---> Oportunidade alterada com sucesso! <---"
                                    +"\nCódigo: "+ op.getCodigo()
                                    +"\n..................................................................");
                            arquivoLista.setRet(0);
                        }catch (Exception ex){
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;

                    case 3: // Consultar oportunidade
                        try{
                            Oportunidade op = (Oportunidade) arquivoLista.getObjetos().get(0);
                            Integer codigo = op.getCodigo();
                            op = consultar(codigo);
                            arquivoLista.setRetorno
                                    ("\n..................................................................\n"
                                    +"         ---> Oportunidade consultada com sucesso! <---" 
                                    +"\n" + "Código: "+ op.getCodigo()
                                    +"\n" + "Descrição: "+ op.getDescricao()
                                    +"\n" + "Código do cargo: "+ op.getCodcargo()
                                    +"\n" + "Descrição do cargo: "+ op.getCargo().getDescricao()
                                    +"\n" + "Tipo: "+ op.getCargo().getTipo() + " - " + dao.getTipoDescricao(op.getCargo().getTipo())
                                    +"\n" + "Acesso: "+ op.getAcesso() + " - " + dao.getAcessoDescricao(op.getAcesso())
                                    +"\n" + "Ingresso: "+op.getIngresso()
                                    +"\n" + "Fechada: "+ op.getFechada() 
                                    +"\n..................................................................");
                            arquivoLista.setRet(0);
                        }catch (Exception ex){
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;
                        
                    case 4: //Excluir oportunidade
                        try{
                            Oportunidade op = (Oportunidade) arquivoLista.getObjetos().get(0);
                            Integer codigo = op.getCodigo();
                            excluir(codigo);
                            arquivoLista.setRetorno
                                    ("\n..................................................................\n"
                                    +"         ---> Oportunidade excluída com sucesso! <---" 
                                    +"\nCódigo: "+ op.getCodigo()
                                    +"\n..................................................................");
                            arquivoLista.setRet(0);
                        }catch (Exception ex){
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;


                    case 5: // Listar oportunidades
                        try{
                            Oportunidade op = (Oportunidade) arquivoLista.getObjetos().get(0);
                            Integer codigo = op.getCodigo();
                            Integer codcargo = op.getCodcargo();
                            for (Oportunidade oport : listaOportunidades(codcargo)){
                                listaRetorno.add((Object)oport);
                            }
                            arquivoLista.setObjetos(listaRetorno);
                            arquivoLista.setRetorno
                                    ("\n         ---> Oportunidades listadas com sucesso! <---\n"            
                                    +"..................................................................");
                            arquivoLista.setRet(0);

                        }catch (Exception ex){
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;

                    case 6: // Listar abertas
                        try{
                            Cargo op = (Cargo) arquivoLista.getObjetos().get(0);
                            Integer tipo = op.getTipo();
                            for (Cargo aberta : listaAbertas(tipo)){
                                listaRetorno.add((Object)aberta);
                            }
                            
                            arquivoLista.setObjetos(listaRetorno);
                            arquivoLista.setRetorno
                                    ("         ---> Oportunidades abertas listadas com sucesso! <---\n"
                                    +"\n..................................................................");
                            arquivoLista.setRet(0);
                        }catch (Exception ex){
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;
                    case 7:
                       //System.out.println("Saindo...");
                       //saida.writeObject(arquivoLista);
                       //System.exit(0);
                       break;
                }
                
                saida.writeObject(arquivoLista);
                saida.flush();
            }

        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            try {
                saida.close();
                return;
            }catch (IOException ex){
                System.out.println(ex.getMessage());
            }
        }
         
    }
        
}
