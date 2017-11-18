/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import dominio.Oportunidade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class ArquivoLista implements Serializable{
    
    private Integer operacao;
    private List<Oportunidade> oportunidades;
    private Integer ret;
    String retorno; 
   
    public ArquivoLista() {
    }
       
    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public int getOperacao() {
        return operacao;
    }
    
    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }
    
    public int getRet(){
        return ret;
    }
    
    public void setRet(Integer ret){
        this.ret = ret;
    }
    
    public List<Oportunidade> getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(List<Oportunidade> oportunidades) {
        this.oportunidades = oportunidades;
    }

   
}
