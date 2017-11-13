package BANCO;

import Twitter.Tweet;
import dominio.Cargo;
import dominio.Oportunidade;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anderson
 */
public class Testes {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
            DaoBanco dao = new DaoBanco();
            
            Oportunidade op = new Oportunidade(1126, 1, "Teste oportunidade", 0, new Date());
            
            try {
                dao.adicionar(op);
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }

	}
	
}
