/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import BANCO.DaoBanco;
import dominio.Oportunidade;
import java.util.Date;
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
    public String hello(@WebParam(name = "adiciona") String txt) {
        
        Oportunidade op = new Oportunidade(5, 5, "descrição", 5, new Date());
        DaoBanco db = new DaoBanco();
        try {
            db.adicionar(op);
        } catch (Exception ex) {
            Logger.getLogger(Operacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "altera")
    public String altera(@WebParam(name = "altera") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "exclui")
    public String exclui(@WebParam(name = "exclui") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "consulta")
    public String consulta(@WebParam(name = "consulta") String txt) {
        return "Hello " + txt + " !";
    }
    
     @WebMethod(operationName = "lista_op")
    public String lista_op(@WebParam(name = "lista_op") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "lista_ab")
    public String lista_ab(@WebParam(name = "lista_ab") String txt) {
        return "Hello " + txt + " !";
    }
    
    
}
