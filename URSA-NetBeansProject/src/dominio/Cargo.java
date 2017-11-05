package dominio;

/**
 *
 * @author anderson
 */
public class Cargo {
	private int codcargo;
	private String descricao;
	private int tipo;

	public Cargo(int codcargo, String descricao, int tipo) {
		this.codcargo = codcargo;
		this.descricao = descricao;
		this.tipo = tipo;
	}

        public Cargo() {
            
        }

        
	@Override
	public String toString() {
		return "Cargo{" + "codcargo=" + codcargo + ", descricao=" + descricao + ", tipo=" + tipo + '}';
	}

	public int getTipo() {    
                return tipo;
	}
        
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getCodcargo() {
		return codcargo;
	}

	public void setCodcargo(int codcargo) {
		this.codcargo = codcargo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
