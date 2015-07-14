package opdracht_b.dbconnect;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mchange.v2.c3p0.*;

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
    private static String passwordHC = "root";

    public static ComboPooledDataSource getC3p0() {

        ComboPooledDataSource cpds = new ComboPooledDataSource();
        //cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setJdbcUrl(urlHC);
        cpds.setUser(userHC);
        cpds.setPassword(passwordHC);
        cpds.setMinPoolSize(3);

        System.out.println("Returning ComboPooledDataSource \n");
        return cpds;
    }

    public static Connection connectWithC3p0() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        } catch (InstantiationException ie) {
            System.err.println("Error: " + ie.getMessage());
        } catch (IllegalAccessException iae) {
            System.err.println("Error: " + iae.getMessage());
        }

        ComboPooledDataSource cpds = getC3p0();

        try {
            if (con != null && !con.isClosed()) {
                con.close();
                con = null;
            }
            con = cpds.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("Returning c3p0 Connection " + con);
        return con;
    }

    public static HikariDataSource getHikari() {

        config.setJdbcUrl(urlHC);
        config.setUsername(userHC);
        config.setPassword(passwordHC);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(3);
        config.setIdleTimeout(28740000);
        config.setMaxLifetime(28740000);
        config.setConnectionTimeout(34000);

        HikariDataSource ds = new HikariDataSource(config);

        System.out.println("Returning HikairDataSource \n");
        return ds;
    }

    public static Connection connectWithHikari() throws SQLException {
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
        if (con != null && !con.isClosed()) {
            con.close();
            con = null;
        }
        con = ds.getConnection();

        System.out.println("Returning Hikari Connection " + con);
        return con;
    }

    public static Connection getConnectionWithHikari() throws SQLException, ClassNotFoundException {
        if (con != null && !con.isClosed()) {
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

    public static void rollBackCon() {
        try {
            con.rollback();
        } catch (Exception ex) {
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
