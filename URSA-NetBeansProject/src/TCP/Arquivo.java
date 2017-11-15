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
public class Arquivo implements Serializable{
    
    private Integer operacao;
    private List<Oportunidade> oportunidades;
    private Date data;
    private Integer tipo;
    String retorno; 

    
    public Arquivo() {
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
    
    public int getTipo() {
        return tipo;
    }
    
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public List<Oportunidade> getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(List<Oportunidade> oportunidades) {
        this.oportunidades = oportunidades;
    }

   
}
