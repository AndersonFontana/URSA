package Cliente;

import dominio.Oportunidade;
import java.sql.Timestamp;
import webservices.*;

/**
 *
 * @author Regis
 */

public class ClienteWSPrincipal {
    public static void main(String[] args){
       webservices.Operacoes_Service servico = new webservices.Operacoes_Service();
       webservices.Operacoes port =  servico.getOperacoesPort();
       
       
       Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
       Oportunidade op = new Oportunidade(2, 1, "Descricão 2", 7, dataDeHoje);
       
        
        
        
       
      
        
            
    
    }
}
