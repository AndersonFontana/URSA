package BANCO;

import JDBC.JDBCUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.List;

/**
 *
 * @author anderson
 */
public class DaoBanco {
	public String teste() throws Exception {
		String sql = "SELECT * FROM Oportunidade";
		PreparedStatement pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		return rs.toString();
	}
/* Só um exemplo da disciplina de POO
	public void incluir(Oportunidade objeto) throws Exception {
		String sql = "SELECT id FROM cidade WHERE id = ?";
		PreparedStatement pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
		pstmt.setInt(1, objeto.getId());
		ResultSet rs = pstmt.executeQuery();

		if (!rs.next()) {
			sql = "INSERT INTO cidade (id, nome, estado) VALUES (?,?,?)";
			pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
			pstmt.setInt(1, objeto.getId());
			pstmt.setString(2, objeto.getNome());
			pstmt.setString(3, objeto.getEstado());
			pstmt.executeUpdate();
		} else {
			throw new Exception("Cidade já está na lista.");
		}
	}

	public Cidade obter(Integer id) throws Exception {
		String sql = "SELECT id FROM cidade WHERE id = ?";
		PreparedStatement pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();

		Cidade temp = new Cidade(id);
		if (rs.next()) {
			return new Cidade(rs.getString("nome"), rs.getString("estado"), rs.getInt("id"));
		} else {
			throw new Exception("Cidade não encontrada.");
		}
	}

	public void alterar(Cidade objeto) throws Exception {
		String sql = "UPDATE cidade SET nome = ?, estado = ? WHERE id = ?";
		PreparedStatement pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
		pstmt.setString(1, objeto.getNome());
		pstmt.setString(2, objeto.getEstado());
		pstmt.setInt(3, objeto.getId());
		pstmt.executeUpdate();
	}

	public void excluir(Cidade objeto) throws Exception {
		String sql = "DELETE FROM cidade WHERE id = ?";
		PreparedStatement pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
		pstmt.setInt(1, objeto.getId());
		pstmt.executeUpdate();
	}

	public void excluir(Integer index) throws Exception {
		String sql = "DELETE FROM cidade WHERE id = ?";
		PreparedStatement pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
		System.out.println(index);
		pstmt.setInt(1, index);
		pstmt.executeUpdate();
	}

	public List<Cidade> obterTodos() throws Exception {
		String sql = "SELECT * FROM cidade";
		PreparedStatement pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		List<Cidade> cidades = new ArrayList();
		while (rs.next()) {
			cidades.add(new Cidade(rs.getString("nome"), rs.getString("estado"), rs.getInt("id")));
		}

		return cidades;
	}

	public List<Cidade> obterTodos(String ordem) throws Exception {
		List<Cidade> cidades = obterTodos();
		switch (ordem) {
			case "ID (a..z)":
				Collections.sort(cidades, Cidade.BY_ID);
				break;
			case "ID (z..a)":
				Collections.sort(cidades, Cidade.BY_ID);
				Collections.reverse(cidades);
				break;
			case "Cidade (a..z)":
				Collections.sort(cidades, Cidade.BY_NOME);
				break;
			case "Cidade (z..a)":
				Collections.sort(cidades, Cidade.BY_NOME);
				Collections.reverse(cidades);
				break;
			case "Estado (a..z)":
				Collections.sort(cidades, Cidade.BY_UF);
				break;
			case "Estado (z..a)":
				Collections.sort(cidades, Cidade.BY_UF);
				Collections.reverse(cidades);
				break;
			default:
				throw new Exception("Cidade não foi possível ordenar, verificar argumento.");
		}

		return cidades;
	}
*/
}
