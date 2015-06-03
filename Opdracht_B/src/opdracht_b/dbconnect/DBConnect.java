package opdracht_b.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Geert
 */
public class DBConnect {

    private static Connection con;
    private static String url = "jdbc:mysql://localhost/oefen_opdracht_db";
    private static String user = "root";
    private static String password = "rsv1er";

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        } catch (InstantiationException ie) {
            System.err.println("Error: " + ie.getMessage());
        } catch (IllegalAccessException iae) {
            System.err.println("Error: " + iae.getMessage());
        }
        
        con = DriverManager.getConnection(url, user, password);
        return con;
    }
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(con != null && !con.isClosed())
            return con;
        connect();
        return con;
    }
}
