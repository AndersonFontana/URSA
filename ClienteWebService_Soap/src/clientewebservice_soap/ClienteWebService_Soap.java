/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientewebservice_soap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import webservices.Oportunidade;

/**
 *
 * @author Regis
 */
public class ClienteWebService_Soap {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        adiciona(10001, 1, "teste2", 1, "22/04/2007");
            
        /*
        if(exclui(1)){
            System.out.println("OK");
        }
        
        */
        Oportunidade op = new Oportunidade();
        op = consulta(10001);
     
        
        
       
        System.out.println("Timestamp: " + op.getDescricao());
        
        
        /*
        List<Oportunidade> oportunidades = new ArrayList();
        oportunidades = listaOp(1);
        System.out.println(oportunidades.size());
        for(int i=0;i<oportunidades.size();i++){
            System.out.println("Op: " + oportunidades.get(i).getDescricao());  
        }
/*
        List<Oportunidade> oportunidade= new ArrayList();
        oportunidade = listaAb(2);
        System.out.println(oportunidade.size());
        for(int i=0;i<oportunidade.size();i++){
            System.out.println("Ab: " + oportunidade.get(i).getDescricao());  
        }
        */
    }

    protected static boolean adiciona(java.lang.Integer codigo, java.lang.Integer codCargo, java.lang.String descricao, java.lang.Integer acesso, java.lang.String fechada) {
        webservices.Operacoes_Service service = new webservices.Operacoes_Service();
        webservices.Operacoes port = service.getOperacoesPort();
        return port.adiciona(codigo, codCargo, descricao, acesso, fechada);
    }

    protected static boolean altera(java.lang.Integer codigo, java.lang.Integer codCargo, java.lang.String descricao, java.lang.Integer acesso, java.lang.String fechada) {
        webservices.Operacoes_Service service = new webservices.Operacoes_Service();
        webservices.Operacoes port = service.getOperacoesPort();
        return port.altera(codigo, codCargo, descricao, acesso, fechada);
    }

    protected static Oportunidade consulta(java.lang.Integer consulta) {
        webservices.Operacoes_Service service = new webservices.Operacoes_Service();
        webservices.Operacoes port = service.getOperacoesPort();
        return port.consulta(consulta);
    }

    protected static boolean exclui(java.lang.Integer exclui) {
        webservices.Operacoes_Service service = new webservices.Operacoes_Service();
        webservices.Operacoes port = service.getOperacoesPort();
        return port.exclui(exclui);
    }

    protected static java.util.List<webservices.Oportunidade> listaAb(int listaAb) {
        webservices.Operacoes_Service service = new webservices.Operacoes_Service();
        webservices.Operacoes port = service.getOperacoesPort();
        return port.listaAb(listaAb);
    }

    protected static java.util.List<webservices.Oportunidade> listaOp(int listaOp) {
        webservices.Operacoes_Service service = new webservices.Operacoes_Service();
        webservices.Operacoes port = service.getOperacoesPort();
        return port.listaOp(listaOp);
    }
    
    
    
}
