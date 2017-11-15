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
    private List<Object> objetos;
    private Calendar data;
    private Integer tipo;
    private Integer codigo;// Para retornar ao usuário se deu erro (1) ou sucesso (0)
    String retorno; // mensagem de retorno

    public List<Object> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<Object> objetos) {
        this.objetos = objetos;
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
    
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Arquivo() {
    }
    
    
}
