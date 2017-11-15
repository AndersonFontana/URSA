/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import dominio.Oportunidade;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author user
 */
public class Arquivo implements Serializable{
    private Integer operacao;
    
    private List<Oportunidade> oportunidades;
    
    private Calendar data;
    
    // Para retornar ao usuário se deu erro (1) ou sucesso (0)
    private Integer codigo;
    
    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
    
    // MENSAGEM DE RETORNO
    String retorno;

    public List<Oportunidade> getOportunidades() {
        return oportunidades;
    }

    public void setOportunidades(List<Oportunidade> oportunidades) {
        this.oportunidades = oportunidades;
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

    public void setOperacao(int ope) {
        this.operacao = ope;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer code) {
        this.codigo = code;
    }

    public Arquivo() {
    }
    
    
}
