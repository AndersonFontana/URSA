package BANCO;

import dominio.Oportunidade;
import java.util.Date;

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
            
            
            try {
                dao.adicionar(op);
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }

	}
	
}
