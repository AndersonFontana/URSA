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
 * @author user
 */
public class ThreadServidorTCP extends Thread{

    /**
     * @param args the command line arguments
     */
    
    //static final Logger logger = Logger.getLogger(ClienteTCP.class.getName());
    
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
    
    public static Boolean inserir(Integer codigo, Integer codcargo, String descricao, Integer acesso, Date fechada) throws Exception {
        try {
            Oportunidade op = new Oportunidade(codigo, codcargo, descricao, acesso, fechada);
            DaoBanco dao = new DaoBanco();
            dao.adicionar(op);
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Não foi possivel inserir a oportunidade!");
        } catch (Exception ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
    
    public static Boolean alterar(Integer codigo, Integer codcargo, String descricao, Integer acesso, Date fechada) throws Exception {
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
        } catch (SQLException e) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Não foi possivel alterar a oportunidade!");
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
    
    public static Boolean excluir(Integer codigo) throws Exception{
        try {
            DaoBanco dao = new DaoBanco();
            //Oportunidade op = new Oportunidade(dao.consultar(codigo).getCodigo());
            dao.excluir(codigo);
            return true;
        }  catch (SQLException e) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Não foi possivel excluir a oportunidade!");
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
            System.out.println("Conexão estabelecida!");

            entrada = new ObjectInputStream(cliente.getInputStream());
            saida = new ObjectOutputStream(cliente.getOutputStream());
            
            while (true){
                Object obj = entrada.readObject();
                Arquivo arquivoLista = (Arquivo) obj;

                Oportunidade op = (Oportunidade) arquivoLista.getObjetos().get(0);
                
                String descricao = op.getDescricao();
                Integer codigo = op.getCodigo();
                Integer codcargo = op.getCodcargo();
                Integer acesso = op.getAcesso();
                Timestamp fechada = op.getFechada();
                //Date fechada = arquivoLista.getData();
                List<Object> listRetorno = new ArrayList<Object>();

                switch (arquivoLista.getOperacao()){
                    case 1: // Adicionar oportunidade
                        try{
                            inserir(codigo, codcargo, descricao, acesso, fechada);
                            arquivoLista.setRetorno("Oportunidade inserida com sucesso!");
                            arquivoLista.setCodigo(0);
                            
                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setCodigo(1);
                        }
                        break;

                    case 2: //Alterar oportunidade
                        try{
                            alterar(codigo, codcargo, descricao, acesso, fechada);
                            arquivoLista.setRetorno("Oportunidade alterada com sucesso!");
                            arquivoLista.setCodigo(0);
                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setCodigo(1);
                        }
                        break;

                    case 3: // Consultar oportunidade
                        try{
                            op = consultar(codigo);
                            listRetorno.add((Object)op);
                            arquivoLista.setObjetos(listRetorno);
                            arquivoLista.setRetorno("Arquivo consultado com sucesso!");
                            arquivoLista.setCodigo(0);
                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setCodigo(1);
                        }
                        break;
                        
                    case 4: //Excluir oportunidade
                        try{
                            excluir(codigo);
                            arquivoLista.setRetorno("Oportunidade excluída com sucesso!");
                            arquivoLista.setCodigo(0);
                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setCodigo(1);
                        }
                        break;


                    case 5: // Listar oportunidades
                        try{
                            for (Oportunidade oport : listaOportunidades(codcargo)){
                                listRetorno.add((Object)oport);
                            }
                            arquivoLista.setObjetos(listRetorno);
                            arquivoLista.setRetorno("Oportunidades listadas com sucesso!");
                            arquivoLista.setCodigo(0);

                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setCodigo(1);
                        }
                        break;

                    case 6: // Listar abertas
                        try{
                            for (Oportunidade aberta : listaAbertas(codcargo)){
                                listRetorno.add((Object)aberta);
                            }
                            arquivoLista.setObjetos(listRetorno);
                            arquivoLista.setRetorno("Abertas listadas com sucesso!");
                            arquivoLista.setCodigo(0);
                        } catch (Exception ex) {
                            arquivoLista.setRetorno(ex.getMessage());
                            arquivoLista.setCodigo(1);
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
    
}
