package BANCO;

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
		String s = new String();
		try {
			s = dao.teste();
		} catch (Exception ex) {
			System.out.println("Err");
		}
		System.out.println(s);
	}
	
}
