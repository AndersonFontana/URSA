package Cliente;

import webservices.*;
/**
 *
 * @author Regis
 */
public class ClienteWSPrincipal {
    public static void main(String[] args){
       webservices.Operacoes_Service servico = new webservices.Operacoes_Service();
       webservices.Operacoes port =  servico.getOperacoesPort();
       
      
        
            
    
    }
}
