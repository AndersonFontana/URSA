/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import BANCO.DaoBanco;
import dominio.Oportunidade;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Regis
 */

@WebService(serviceName = "Operacoes")
public class Operacoes {
    
    @WebMethod(operationName = "adiciona")
    public boolean adiciona(@WebParam(name = "codigo") Integer Codigo,
                            @WebParam(name = "codCargo") Integer CodCargo,
                            @WebParam(name = "descricao") String descricao,
                            @WebParam(name = "acesso") Integer acesso,
                            @WebParam(name = "fechada") Timestamp fechada
                            
            ) {  
        DaoBanco db = new DaoBanco();
        Oportunidade op = new Oportunidade(Codigo, CodCargo, descricao, acesso, fechada);
        try {
            db.adicionar(op);
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    @WebMethod(operationName = "altera")
    public boolean altera(@WebParam(name = "altera") Oportunidade Op) {
        DaoBanco db = new DaoBanco();
        try {
            db.alterar(Op);
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    
    @WebMethod(operationName = "exclui")
    public boolean exclui(@WebParam(name = "exclui") Oportunidade Op) {
         DaoBanco db = new DaoBanco();
        try {
            db.excluir(Op);
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    @WebMethod(operationName = "consulta")
    public Oportunidade consulta(@WebParam(name = "consulta") Oportunidade Op) {
         Oportunidade ret = new Oportunidade();
         DaoBanco db = new DaoBanco();
        try {
            ret = db.consultar(Op);
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ret;
    }
    
     @WebMethod(operationName = "lista_op")
     public List<Oportunidade> lista_op(@WebParam(name = "lista_op") int Codigo) {
        List<Oportunidade> oportunidades = new ArrayList();
         DaoBanco db = new DaoBanco();
        try {
            db.listaOportunidades(Codigo);
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return oportunidades;
    }
    
    @WebMethod(operationName = "lista_ab")
    public List<Oportunidade> lista_ab(@WebParam(name = "lista_ab") int Tipo) {
         List<Oportunidade> oportunidade = new ArrayList();
         DaoBanco db = new DaoBanco();
        try {
            db.listaAbertas(Tipo);
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
         return oportunidade;
    }
    
    
}
