package BANCO;

import JDBC.JDBCUtil;
import dominio.Cargo;
import dominio.Oportunidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anderson
 */
public class DaoBanco {
    private String sql;
    private PreparedStatement pstmt;
    private ResultSet rs;

    /**
     * Adiciona uma oportunidade ao BD com base no objeto, instancia de
     * oportunidade
     *
     * @param objeto: Instancia de Oportunidade a ser inserido no banco, o
     * Timestamp de ingresso será inserido automaticamente
     * @return true se for executado como o esperado
     * @throws Exception caso já exista o codigo da Oportunidade
     */
    public boolean adicionar(Oportunidade objeto) throws Exception {
        rs = existeCargo(objeto.getCodcargo());
        if (!rs.next()) {
            throw new Exception("Cargo não existe!");
        }

        rs = existeOportunidade(objeto.getCodigo());
        if (!rs.next()) {
            sql = "INSERT INTO Oportunidade VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
            pstmt.setInt(1, objeto.getCodigo());
            pstmt.setInt(2, objeto.getCodcargo());
            pstmt.setString(3, objeto.getDescricao());
            pstmt.setInt(4, objeto.getAcesso());
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pstmt.setTimestamp(6, objeto.getFechada());
            pstmt.executeUpdate();
        } else {
            throw new Exception("Oportunidade já existe!");
        }
        return true;
    }

    /**
     * Altera uma oportunidade ao BD com base no objeto, instancia de
     * oportunidade
     *
     * @param objeto a ser alterado no banco, note que se passar um objeto só
     * com código, irá remover os dados dos outros campos (#TODO)
     * @return true se for executado como o esperado
     * @throws Exception caso já exista o codigo da Oportunidade
     */
    public boolean alterar(Oportunidade objeto) throws Exception {
        if (objeto.getCargo() != null) {
            rs = existeCargo(objeto.getCodcargo());
            if (!rs.next()) {
                throw new Exception("Cargo não existe!");
            }
        }

        rs = existeOportunidade(objeto.getCodigo());
        if (rs.next()) {
            sql = "UPDATE Oportunidade SET codcargo = ?, descricao = ?, acesso = ?, fechada = ? WHERE codigo = ?";
            pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);

            if (objeto.getCodcargo() == 0) {
                pstmt.setInt(1, rs.getInt("codcargo"));
            } else {
                pstmt.setInt(1, objeto.getCodcargo());
            }

            if (objeto.getDescricao() == null) {
                pstmt.setString(2, rs.getString("descricao"));
            } else {
                pstmt.setString(2, objeto.getDescricao());
            }

            if (objeto.getAcesso() == 0) {
                pstmt.setInt(3, rs.getInt("acesso"));
            } else {
                pstmt.setInt(3, objeto.getAcesso());
            }

            if (objeto.getFechada() == null) {
                pstmt.setTimestamp(4, rs.getTimestamp("fechada"));
            } else {
                pstmt.setTimestamp(4, objeto.getFechada());
            }

            pstmt.setInt(5, objeto.getCodigo());
            pstmt.executeUpdate();
        } else {
            throw new Exception("Oportunidade não encontrada!");
        }
        return true;
    }

    /**
     * Apaga uma oportunidade do BD com base no código da oportunidade
     *
     * @param codigo identificador da Oportunidade a ser excluído
     * @return true se for executado como o esperado
     * @throws Exception caso a Oportunidade não for encontrada
     */
    public boolean excluir(int codigo) throws Exception {
        rs = existeOportunidade(codigo);
        if (rs.next()) {
            sql = "DELETE FROM Oportunidade WHERE codigo = ?";
            pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
            pstmt.setInt(1, codigo);
            pstmt.executeUpdate();
        } else {
            throw new Exception("Oportunidade não encontrada!");
        }
        return true;
    }

    /**
     * Apaga uma oportunidade do BD com base no objeto, instancia de
     * oportunidade
     *
     * @param objeto Instancia da Oportunidade a ser excluído
     * @return true se for executado como o esperado
     * @throws Exception caso a Oportunidade não for encontrada
     */
    public boolean excluir(Oportunidade objeto) throws Exception {
        return excluir(objeto.getCodigo());
    }

    /**
     * Retorna os dados de uma oportunidade com base no código da oportunidade
     *
     * @param codigo identificador da Oportunidade a ser consultado
     * @return Instancia de Oportunidade com todos os dados, incluindo detalhes
     * do Cargo
     * @throws Exception caso a Oportunidade não seja encontrada
     */
    public Oportunidade consultar(int codigo) throws Exception {
        rs = existeOportunidade(codigo);
        if (rs.next()) {
            return new Oportunidade(
                    rs.getInt("codigo"),
                    rs.getInt("codcargo"),
                    consultarCargo(rs.getInt("codcargo")),
                    rs.getString("descricao"),
                    rs.getInt("acesso"),
                    rs.getTimestamp("ingresso"),
                    rs.getTimestamp("fechada")
            );
        } else {
            throw new Exception("Oportunidade não encontrada!");
        }
    }

    /**
     * Retorna os dados de uma oportunidade com base no objeto, instancia de
     * oportunidade
     *
     * @param objeto Instancia da Oportunidade a ser consultado
     * @return Instancia de Oportunidade com todos os dados, incluindo detalhes
     * do Cargo
     * @throws Exception caso a Oportunidade não seja encontrada
     */
    public Oportunidade consultar(Oportunidade objeto) throws Exception {
        return consultar(objeto.getCodigo());
    }

    /**
     * Recebe o código do cargo e retorna uma lista contendo as oportunidades
     * para aquele cargo
     *
     * @param CodCargo
     * @return Lista com todas as Oportunidades encontradas
     * @throws Exception caso o Cargo não seja encontrado
     */
    public List<Oportunidade> listaOportunidades(int CodCargo) throws Exception {
        rs = existeCargo(CodCargo);
        if (!rs.next()) {
            throw new Exception("Cargo não existe!");
        }

        sql = "SELECT * FROM Oportunidade WHERE codcargo = ?";
        pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
        pstmt.setInt(1, CodCargo);

        return resultSet2ArrayList(pstmt.executeQuery());
    }

    /**
     * Recebe o tipo de oportunidade e retorna as oportunidades em aberto para
     * aquele tipo
     *
     * @param tipo
     * @return Lista com todas as Oportunidades encontradas
     * @throws Exception caso tipo não seja encontrado em Cargo
     */
    public List<Oportunidade> listaAbertas(int tipo) throws Exception {
        sql = "SELECT * FROM Cargo WHERE tipo = ?";
        pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
        pstmt.setInt(1, tipo);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            sql = "SELECT o.* FROM Oportunidade o, Cargo c WHERE ? < o.fechada AND o.codcargo = c.codcargo AND c.tipo = ?";
            pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
            pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(2, tipo);
        } else {
            throw new Exception("Tipo não encontrado em Cargo");
        }

        return resultSet2ArrayList(pstmt.executeQuery());
    }

    /**
     * Retorna as oportunidades em aberto para todos os tipos
     *
     * @return Lista com todas as Oportunidades encontradas
     * @throws Exception
     */
    public List<Oportunidade> listaAbertas() throws Exception {
        sql = "SELECT * FROM Oportunidade WHERE ? < fechada";
        pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
        pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));

        return resultSet2ArrayList(pstmt.executeQuery());
    }

    /**
     * Consultar Cargo especifico passando o codcargo
     *
     * @param codcargo
     * @return instancia de Cargo com todas as informações
     * @throws Exception caso codcargo não seja encontrado
     */
    public Cargo consultarCargo(int codcargo) throws Exception {
        ResultSet rs2 = existeCargo(codcargo);
        if (rs2.next()) {
            return new Cargo(
                    rs2.getInt("codcargo"),
                    rs2.getString("descricao"),
                    rs2.getInt("tipo")
            );
        } else {
            throw new Exception("Cargo não encontrado!");
        }
    }

    private ResultSet existeOportunidade(int codigo) throws Exception {
        sql = "SELECT * FROM Oportunidade WHERE codigo = ?";
        pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
        pstmt.setInt(1, codigo);
        return pstmt.executeQuery();
    }

    private ResultSet existeCargo(int codcargo) throws Exception {
        sql = "SELECT * FROM Cargo WHERE codcargo = ?";
        pstmt = JDBCUtil.getInstance().getCon().prepareStatement(sql);
        pstmt.setInt(1, codcargo);
        return pstmt.executeQuery();
    }

    private List<Oportunidade> resultSet2ArrayList(ResultSet rs) throws Exception {
        List<Oportunidade> oportunidades = new ArrayList();
        while (rs.next()) {
            oportunidades.add(new Oportunidade(
                    rs.getInt("codigo"),
                    rs.getInt("codcargo"),
                    consultarCargo(rs.getInt("codcargo")),
                    rs.getString("descricao"),
                    rs.getInt("acesso"),
                    rs.getTimestamp("ingresso"),
                    rs.getTimestamp("fechada")
            )
            );
        }
        return oportunidades;
    }
}
