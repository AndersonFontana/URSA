package BANCO;

import Twitter.Tweet;
import dominio.Cargo;
import dominio.Oportunidade;
import java.sql.Timestamp;
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
                Oportunidade op = new Oportunidade();
                
            try {
                op = dao.consultar(1);
            } catch (Exception ex) {
                System.out.println("Erro");
            }            
            Tweet tw = new Tweet();
            tw.Tweet(op);
                
                //Timestamp now = new Timestamp(System.currentTimeMillis());
                //Timestamp tstp = new Timestamp(System.currentTimeMillis()+2000000000);
                //Oportunidade op = new Oportunidade(2, 1, "Descrição 2", 7, tstp);
                //Oportunidade opp = new Oportunidade();
                //opp.setCodigo(1);
                
                // System.out.println("'"+tstp+"'");
                // String s = new String();
                // boolean s = false;
		
//		List<Oportunidade> listaOportunidades = null;
//		Cargo cargo = null;
//		
//		try {
//			//System.out.println(dao.adicionar(op));
//			// System.out.println(dao.alterar(op));
//			// System.out.println(dao.consultar(op).toString());
//			// System.out.println(dao.excluir(op));
//			listaOportunidades = dao.listaOportunidades(1);
//			// listaOportunidades = dao.listaAbertas();
//			listaOportunidades = dao.listaAbertas(7);
//			cargo = dao.consultarCargo(1);
//			
//			System.out.println(cargo.toString());
//
//			System.out.println(listaOportunidades.size());
//			for (int i = 0; i < listaOportunidades.size(); i++) {
//				System.out.println(listaOportunidades.get(i).toString());
//			}
//			
//		} catch (Exception ex) {
//			System.out.println("Err: " + ex.getMessage());
//		}


	}
	
}
