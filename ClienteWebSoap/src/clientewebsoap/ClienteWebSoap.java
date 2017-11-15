/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientewebsoap;

/**
 *
 * @author Regis
 */
public class ClienteWebSoap {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        adiciona(10, 1, "teste", 1, "22/01/2004");
    }

    private static boolean adiciona(java.lang.Integer codigo, java.lang.Integer codCargo, java.lang.String descricao, java.lang.Integer acesso, java.lang.String fechada) {
        webservices.Operacoes_Service service = new webservices.Operacoes_Service();
        webservices.Operacoes port = service.getOperacoesPort();
        return port.adiciona(codigo, codCargo, descricao, acesso, fechada);
    }
    
}
