/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Daniela
 */
public class ArquivoBD implements Serializable {
    

    private Integer ope;
    
    // LISTA DE OBJETOS DO TIPO SELECIONADO
    private List<Object> objetos;
    
    private Calendar data;
    
    // 1 - Erro / 0 - Sucesso
    private Integer code;

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
    
    // MENSAGEM DE RETORNO
    String retorno;

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

    public int getOpe() {
        return ope;
    }

    public void setOpe(int ope) {
        this.ope = ope;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public ArquivoBD() {
    }
}
