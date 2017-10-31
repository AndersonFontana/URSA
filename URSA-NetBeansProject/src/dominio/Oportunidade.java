package dominio;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author anderson
 */
public class Oportunidade {
	private int codigo;
	private int codcargo;
	private Cargo cargo;
	private String descricao;
	private int acesso;
	private Timestamp ingresso;
	private Timestamp fechada;

	public Oportunidade(int codigo, int codcargo, Cargo cargo, String descricao, int acesso, Timestamp ingresso, Timestamp fechada) {
		this.codigo = codigo;
		this.codcargo = codcargo;
		this.cargo = cargo;
		this.descricao = descricao;
		this.acesso = acesso;
		this.ingresso = ingresso;
		this.fechada = fechada;
	}

	public Oportunidade(int codigo, int codcargo, String descricao, int acesso, Timestamp fechada) {
		this.codigo = codigo;
		this.codcargo = codcargo;
		this.descricao = descricao;
		this.acesso = acesso;
		this.fechada = fechada;
	}

	@Override
	public String toString() {
		return "Oportunidade{" + "codigo=" + codigo + ", codcargo=" + codcargo + ", cargo=" + cargo + ", descricao=" + descricao + ", acesso=" + acesso + ", ingresso=" + ingresso + ", fechada=" + fechada + '}';
	}
	
	public Oportunidade(int codigo, int codcargo, String descricao, int acesso, Date fechada) {
		this.codigo = codigo;
		this.codcargo = codcargo;
		this.descricao = descricao;
		this.acesso = acesso;
		this.fechada = new Timestamp(fechada.getTime());
	}

	public Oportunidade() {
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public int getAcesso() {
		return acesso;
	}

	public void setAcesso(int acesso) {
		this.acesso = acesso;
	}

	public Timestamp getIngresso() {
		return ingresso;
	}

	public Timestamp getFechada() {
		return fechada;
	}

	public void setFechada(Timestamp fechada) {
		this.fechada = fechada;
	}
	
	public void setFechada(Date fechada) {
		this.fechada = new Timestamp(fechada.getTime());
	}

	public Cargo getCargo() {
		return cargo;
	}
}
