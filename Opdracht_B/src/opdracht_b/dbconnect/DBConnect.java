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
    private static String url;
    private static String user;
    private static String password;
    private static String urlHC = "jdbc:mysql://localhost/oefen_opdracht_db";
    private static String userHC = "root";
    private static String passwordHC = "rsv1er";

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
        
        con = DriverManager.getConnection(urlHC, userHC, passwordHC);
        
        return con;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (con != null && !con.isClosed()) {
            return con;
        }
        connect();
        return con;
    }
    public static void rollBackCon(){
        try{
            con.rollback();
        }
        catch(Exception ex){
            ex.printStackTrace();
                    
        }
    }

    public static void setUrl(String aUrl) {
        url = aUrl;
    }

    public static void setUser(String aUser) {
        user = aUser;
    }

    public static void setPassword(String aPassword) {
        password = aPassword;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser() {
        return user;
    }

    public static String getPassword() {
        return password;
    }
}
