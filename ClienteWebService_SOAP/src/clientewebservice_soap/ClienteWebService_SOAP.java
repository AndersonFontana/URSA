/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientewebservice_soap;

import webservices.Oportunidade;

/**
 *
 * @author Regis
 */
public class ClienteWebService_SOAP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       adiciona(10009, 1, "teste2", 1, "22/04/2007"); 

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
