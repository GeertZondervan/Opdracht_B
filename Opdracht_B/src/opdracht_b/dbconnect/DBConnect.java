package opdracht_b.dbconnect;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Geert
 */
public class DBConnect {

    private static Connection con;
    private static HikariConfig config = new HikariConfig();
    private static String url;
    private static String user;
    private static String password;
    private static String urlHC = "jdbc:mysql://localhost/oefen_opdracht_db";
    private static String userHC = "root";
    private static String passwordHC = "rsv1er";

    
    public static HikariDataSource getHikari(){
        
       
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(2);
        config.setInitializationFailFast(true);
        config.setConnectionTestQuery("VALUES 1");
        
        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        //config.addDataSourceProperty("serverName", "localhost");
        config.addDataSourceProperty("port", "3306");
    //    config.addDataSourceProperty("databaseName", "oefen_opdracht_db");
        
        config.setJdbcUrl(urlHC);
        config.setUsername(userHC);
        config.setPassword(password);
        
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(urlHC);
        ds.setUsername(userHC);
        ds.setPassword(password);
       // ds.setDriverClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        return ds;
    }
    
    public static Connection connectWithHikari() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        } catch (InstantiationException ie) {
            System.err.println("Error: " + ie.getMessage());
        } catch (IllegalAccessException iae) {
            System.err.println("Error: " + iae.getMessage());
        }
        
        HikariDataSource ds = getHikari();
        con = ds.getConnection();
        return con;
    }
    
    public static Connection getConnectionWithHikari() throws SQLException, ClassNotFoundException{
        if(con != null && !con.isClosed()){
            return con;
        }
        connectWithHikari();
        return con;
    }
    
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
