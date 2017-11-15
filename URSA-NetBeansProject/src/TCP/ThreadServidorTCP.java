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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ThreadServidorTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }

    void start() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Socket s;
    
    public ThreadServidorTCP(Socket cliente){
        this.s = s;
    }

    public Socket getS() {
        return s;
    }

    public void setS(Socket s) {
        this.s = s;
    }
    
    public static Boolean inserir(Integer codigo, Integer codcargo, String descricao, Integer acesso, Timestamp fechada) throws Exception {
        try {
            Oportunidade op = new Oportunidade(codigo, codcargo, descricao, acesso, fechada);
            DaoBanco dao = new DaoBanco();
            dao.adicionar(op);
            //Logger.logMethod("TCP", "");
            return true;
        } catch (SQLException e) {
            //Logger.logMethod("TCP", e.getMessage());
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Não foi possivel inserir a oportunidade.");
        } catch (Exception ex) {
            //Logger.logMethod("TCP",  ex.getMessage());
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
    
    public static Boolean alterar(Integer Codigo) throws Exception {
        try {
            DaoBanco dao = new DaoBanco();
            Oportunidade op;
            op = new Oportunidade(dao.consultar(Codigo).getCodigo());
            
            dao.alterar(op);
            //Logger.logMethod("TCP", "");
            return true;
        } catch (SQLException e) {
            //Logger.logMethod("TCP", e.getMessage());
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Não foi possivel alterar a oportunidade.");
        } catch (Exception ex) {
            //Logger.logMethod("TCP",  ex.getMessage());
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
   

    public static Oportunidade consultar(Integer Codigo) throws Exception{
        try {
            DaoBanco dao = new DaoBanco();
            Oportunidade op = dao.consultar(Codigo);
            //Logger.logMethod("TCP", "");
            return op;
        } catch (Exception ex) {
            //Logger.logMethod("TCP",  ex.getMessage());
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
    
    public static Boolean excluir(Integer Codigo) throws Exception{
        try {
            DaoBanco dao = new DaoBanco();
            Oportunidade op = new Oportunidade(dao.consultar(Codigo).getCodigo());
            dao.excluir(op);
            //Logger.logMethod("TCP", "");
            return true;
        }  catch (SQLException e) {
            //Logger.logMethod("TCP", e.getMessage());
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Não foi possivel excluir a oportunidade.");
        } catch (Exception ex) {
            //Logger.logMethod("TCP",  ex.getMessage());
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }

    public static List<Oportunidade> listarOportunidades(Integer Codcargo) throws Exception{
        try {
            DaoBanco dao = new DaoBanco();
            List<Oportunidade> list = (List<Oportunidade>) dao.consultar(Codcargo);

            for (Oportunidade op : list) {
                op.setCodcargo(Codcargo);
            }
            //Logger.logMethod("TCP", "");
            return list;
        } catch (Exception ex) {
            //Logger.logMethod("TCP",  ex.getMessage());
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
    
    public static List<Oportunidade> listarAbertas(Integer tipo) throws Exception{
        try {
            DaoBanco dao = new DaoBanco();
            List<Oportunidade> list = (List<Oportunidade>) dao.consultar(tipo);

            for (Oportunidade op : list) {
                op.setCodcargo(tipo);
            }
            //Logger.logMethod("TCP", "");
            return list;
        } catch (Exception ex) {
            //Logger.logMethod("TCP",  ex.getMessage());
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }

    //@SuppressWarnings("empty-statement")
    public void run()
    {
        ObjectInputStream entrada = null;
        ObjectOutputStream saida = null;
        
        try {
            System.out.println("Conexão estabelecida.");

            entrada = new ObjectInputStream(s.getInputStream());
            saida = new ObjectOutputStream(s.getOutputStream());
            
            while (true){
                Object obj = entrada.readObject();
                Arquivo arquivoLista = (Arquivo) obj;

                Oportunidade op = (Oportunidade) arquivoLista.getOportunidades().get(0);

                String descricao = op.getDescricao();
                Integer codigo = op.getCodigo();
                Integer codcargo = op.getCodcargo();
                Integer acesso = op.getAcesso();
                Calendar fechada = arquivoLista.getData();
                List<Oportunidade> listRetorno = new ArrayList<Oportunidade>();

                switch (arquivoLista.getOperacao()){
                    case 1: // Adicionar oportunidade
                        try{
                            adicionar(codigo, codcargo, descricao, acesso, fechada);
                            //arquivoLista.setRetorno("Oportunidade inserida com sucesso");
                            //arquivoLista.setCodigo(0);
                        } catch (Exception ex) {
                            //arquivoLista.setRetorno(ex.getMessage());
                            //arquivoLista.setCodigo(1);
                        }
                        break;

                    case 2: //Alterar oportunidade

                        try{
                            alterar(codigo);
                            //arquivoLista.setRetorno("Oportunidade alterada com sucesso");
                            //arquivoLista.setCodigo(0);
                        } catch (Exception ex) {
                            //arquivoLista.setRetorno(ex.getMessage());
                            //arquivoLista.setCodigo(1);
                        }
                        break;

                    case 3: // Consultar oportunidade

                        try{
                            excluir(codigo);
                            //arquivoLista.setRetorno("Oportunidade excluído com sucesso");
                            //arquivoLista.setCodigo(0);
                        } catch (Exception ex) {
                            //arquivoLista.setRetorno(ex.getMessage());
                            //arquivoLista.setCodigo(1);
                        }
                        break;

                    case 4: //Excluir oportunidade

                        try{
                            op = consultar(codigo);
                            listRetorno.add((Oportunidade)op);
                            arquivoLista.setOportunidades(listRetorno);
                            //arquivoLista.setRetorno("Arquivo consultado com sucesso.");
                            //arquivoLista.setCodigo(0);
                        } catch (Exception ex) {
                            //arquivoLista.setRetorno(ex.getMessage());
                            //arquivoLista.setCodigo(1);
                        }
                        break;

                    case 5: // Listar oportunidades

                        try{
                            for (Oportunidade opor : listaOp(codcargo)){
                                listRetorno.add((Oportunidade)opor);
                            }

                            arquivoLista.setOportunidades(listRetorno);
                            //arquivoLista.setRetorno("Oportunidades listadas com sucesso.");
                            //arquivoLista.setCodigo(0);

                        } catch (Exception ex) {
                            //arquivoLista.setRetorno(ex.getMessage());
                            //arquivoLista.setCodigo(1);
                        }
                        break;

                    case 6: // Listar abertas
                        try{
                            for (Oportunidade aber : listaAberta(codcargo)){
                                listRetorno.add((Oportunidade)aber);
                            }
                            
                            arquivoLista.setOportunidades(listRetorno);
                            //arquivoLista.setRetorno("Abertas listadas com sucesso.");
                            //arquivoLista.setCodigo(0);
                        } catch (Exception ex) {
                            //arquivoLista.setRetorno(ex.getMessage());
                            //arquivoLista.setCodigo(1);
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

    private void adicionarOportunidade(Integer codigo, Integer codcargo, String descricao, Integer acesso, Calendar fechada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void excluirOportunidade(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Iterable<Oportunidade> listaOp(Integer codcargo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void adicionar(Integer codigo, Integer codcargo, String descricao, Integer acesso, Calendar fechada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Iterable<Oportunidade> listaAberta(Integer codcargo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
