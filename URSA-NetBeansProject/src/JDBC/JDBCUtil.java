package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anderson
 */
public class JDBCUtil {
    
    private Connection con;
    
    private JDBCUtil() {
        
        try {
            // Registrar drive JDBC
            Class.forName("org.postgresql.Driver");

            // Montar a url do JDBC
            String url = "jdbc:postgresql://ec2-184-73-189-190.compute-1.amazonaws.com:5432/df4dv32d0ha20c?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

            // instanciar um objedo da classe Connection
            con = DriverManager.getConnection(url, "jgnctpthhqngqk", "6a4f0f2a99e6d6109658af60edced243a6fa8c2ff9eac54d474be2cc584303bd");
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            System.out.println("Erro de conexão " + classNotFoundException.getMessage());
        }

    }
    
    public static JDBCUtil getInstance() {
        return JDBCUtilHolder.INSTANCE;
    }

    public Connection getCon() {
        return con;
    }

    private static class JDBCUtilHolder {
        private static final JDBCUtil INSTANCE = new JDBCUtil();
    }
}
