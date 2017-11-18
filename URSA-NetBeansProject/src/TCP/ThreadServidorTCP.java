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
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniela
 */
public class ThreadServidorTCP extends Thread{

    /**
     * @param args the command line arguments
     */
      
    public static Boolean inserir(Integer codigo, Integer codcargo, String descricao, Integer acesso, Date fechada) throws Exception {
//        Oportunidade op = new Oportunidade(codigo, codcargo, descricao, acesso, fechada);
//        DaoBanco dao = new DaoBanco();
//        String s = "Oportunidade incluída com sucesso! Código: "+op.getCodigo();
//        try {
//            dao.adicionar(op);
//        } catch (SQLException ex) {
//            //Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
//            s = ex.getMessage();
//        }
//        return s;
        
        try {
            Oportunidade op = new Oportunidade(codigo, codcargo, descricao, acesso, fechada);
            DaoBanco dao = new DaoBanco();
            dao.adicionar(op);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
    
    public static Boolean alterar(Integer codigo, Integer codcargo, String descricao, Integer acesso, Date fechada) throws Exception {
//        Oportunidade op = new Oportunidade(codigo, codcargo, descricao, acesso, fechada);
//        DaoBanco dao = new DaoBanco();
//        String s = "Oportunidade alterada com sucesso! Código: "+op.getCodigo();
//        op.setCodigo(codigo);
//        op.setAcesso(acesso);
//        op.setCodcargo(codcargo);
//        op.setDescricao(descricao);
//        op.setFechada(fechada);
//        try {
//            dao.alterar(op);
//        } catch (Exception ex) {
//            //Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
//            s = ex.getMessage();
//        }
//        return s;

        try {
            DaoBanco dao = new DaoBanco();
            Oportunidade op = new Oportunidade(codigo, codcargo, descricao, acesso, fechada);
            op.setCodigo(codigo);
            op.setAcesso(acesso);
            op.setCodcargo(codcargo);
            op.setDescricao(descricao);
            op.setFechada(fechada);
            dao.alterar(op);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
   

    public static Oportunidade consultar(Integer Codigo) throws Exception{
        try {
            DaoBanco dao = new DaoBanco();
            Oportunidade op = dao.consultar(Codigo);
            return op;
        } catch (Exception ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
    
    public static Boolean excluir(Integer Codigo) throws Exception{
//        DaoBanco dao = new DaoBanco();
//        Oportunidade op = new Oportunidade();
//        String s = "Oportunidade excluída com sucesso! Código: "+op.getCodigo();
//        try {
//            dao.excluir(Codigo);
//        } catch (Exception ex) {
//            //Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
//            s = ex.getMessage();
//        }
//        return s;
        try {
            DaoBanco dao = new DaoBanco();
            dao.excluir(Codigo);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }

    public static List<Oportunidade> listaOportunidades(Integer Codcargo) throws Exception{
        try {
            DaoBanco dao = new DaoBanco();
            List<Oportunidade> list = (List<Oportunidade>) dao.consultar(Codcargo);

            for (Oportunidade op : list) {
                op.setCodcargo(Codcargo);
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
    
    public static List<Oportunidade> listaAbertas(Integer tipo) throws Exception{
        try {
            DaoBanco dao = new DaoBanco();
            List<Oportunidade> list = (List<Oportunidade>) dao.consultar(tipo);

            for (Oportunidade op : list) {
                op.setCodcargo(tipo);
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }

    public void run()
    {
        ObjectInputStream entrada = null;
        ObjectOutputStream saida = null;
        
        try {
            entrada = new ObjectInputStream(cliente.getInputStream());
            saida = new ObjectOutputStream(cliente.getOutputStream());
            
            while (true){
                Object obj = entrada.readObject();
                ArquivoLista arquivoLista = (ArquivoLista) obj;

                Oportunidade op = (Oportunidade) arquivoLista.getOportunidades().get(0);
                
                String descricao = op.getDescricao();
                Integer codigo = op.getCodigo();
                Integer codcargo = op.getCodcargo();
                Integer acesso = op.getAcesso();
                Date fechada = op.getFechada();
                List<Oportunidade> listRetorno = new ArrayList<Oportunidade>();

                switch (arquivoLista.getOperacao()){
                    case 1: // Adicionar oportunidade
                        try{
                            inserir(codigo, codcargo, descricao, acesso, fechada);
                            arquivoLista.setRetorno("\n..................................................................\n"
                                    +"---> Oportunidade inserida com sucesso! <---" 
                                    +"\nCódigo: "+ op.getCodigo()
                                    +"\n..................................................................");
                            arquivoLista.setRet(0);
                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;

                    case 2: //Alterar oportunidade
                        try{
                            alterar(codigo, codcargo, descricao, acesso, fechada);
                            arquivoLista.setRetorno("\n..................................................................\n"
                                    +"---> Oportunidade alterada com sucesso! <---"
                                    +"\nCódigo: "+ op.getCodigo()
                                    +"\n..................................................................");
                            arquivoLista.setRet(0);
                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;

                    case 3: // Consultar oportunidade
                        try{
                            op = consultar(codigo);
                            listRetorno.add((Oportunidade)op);
                            arquivoLista.setOportunidades(listRetorno);
                            arquivoLista.setRetorno("\n..................................................................\n"
                                    +"---> Oportunidade consultada com sucesso! <---" 
                                    +"\n" + "Código: "+ op.getCodigo()
                                    +"\n" + "Descrição: "+ op.getDescricao()
                                    +"\n" + "Cargo: "+ op.getCargo()
                                    +"\n" + "Código Cargo: "+ op.getCodcargo()
                                    +"\n" + "Acesso: "+ op.getAcesso()
                                    +"\n" + "Ingresso: "+ op.getIngresso()
                                    +"\n" + "Fechada: "+ op.getFechada() 
                                    +"\n..................................................................");
                            arquivoLista.setRet(0);
                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;
                        
                    case 4: //Excluir oportunidade
                        try{
                            excluir(codigo);
                            arquivoLista.setRetorno("\n..................................................................\n"
                                    +"---> Oportunidade excluída com sucesso! <---" 
                                    +"\nCódigo: "+ op.getCodigo()
                                    +"\n..................................................................");
                            //System.out.println(arquivoLista.getRetorno());
                            arquivoLista.setRet(0);
                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;


                    case 5: // Listar oportunidades
                        try{
                            for (Oportunidade oport : listaOportunidades(codcargo)){
                                listRetorno.add((Oportunidade)oport);
                            }
                            arquivoLista.setOportunidades(listRetorno);
                            arquivoLista.setRetorno("\n..................................................................\n"
                                    +"---> Oportunidades listadas com sucesso! <---\n"
                                    + listRetorno
                                    +"\n..................................................................");
                            //System.out.println(arquivoLista.getRetorno());
                            arquivoLista.setRet(0);

                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;

                    case 6: // Listar abertas
                        try{
                            for (Oportunidade aberta : listaAbertas(codcargo)){
                                listRetorno.add((Oportunidade)aberta);
                            }
                            arquivoLista.setOportunidades(listRetorno);
                            arquivoLista.setRetorno("\n...................................................................\n"
                                    +"---> Oportunidades abertas listadas com sucesso! <---\n"
                                    + listRetorno
                                    +"\n..................................................................");
                            //System.out.println(arquivoLista.getRetorno());
                            arquivoLista.setRet(0);
                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setRet(1);
                        }
                        break;
                    case 7:
                        return;
                }
                
                saida.writeObject(arquivoLista);
                saida.flush();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                saida.close();
                return;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
         
    }
    
    private Socket cliente;
    
    public ThreadServidorTCP(Socket cliente){
        this.cliente = cliente;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }
    
    
}
