package opdracht_b;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import javax.sql.rowset.CachedRowSet;
import opdracht_b.dbconnect.DBConnect;
import opdracht_b.pojo.Klant;

/**
 *
 * @author Geert
 */
public class AppFunctionality {

    private static CachedRowSet crs;
    private Connection con;

    public void setConnection(String url, String user, String password) {
        DBConnect.setUrl(url);
        DBConnect.setUser(user);
        DBConnect.setPassword(password);
    }

    public void setCrs() {
        try {
            crs = new CachedRowSetImpl();
            con = DBConnect.connect();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error creating crs");

        }
    }

    public void createKlant(Klant klant) {
        try {
            setCrs();
            crs.setCommand("insert into oefen_opdracht_db.Klant (voornaam, achternaam, tussenvoegsel, email, straatnaam, postcode, toevoeging, huisnummer, woonplaats) VALUES ( '"
                    + klant.getVoornaam() + "', '" + klant.getAchternaam() + "', '" + klant.getTussenvoegsel() + "', '" + klant.getEmail() + "', '" + klant.getStraatnaam() + "', '" + klant.getPostcode() + "', '" + klant.getToevoeging() + "', '"
                    + klant.getHuisnummer() + "', '" + klant.getWoonplaats() + "')");
            crs.execute(con);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error inserting klant into database");
        }

    }

    public void readKlant(int klandId) {
        try {
            setCrs();
            crs.setCommand("SELECT voornaam, achternaam, tussenvoegsel, email, straatnaam, postcode, toevoeging, huisnummer, woonplaats FROM  Klant WHERE klant_id = '" + klandId + "';");
            crs.execute(con);
            while (crs.next()) {
                System.out.println(crs.getString(2));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error readin Klant");
        }

    }

    public void readKlant(String voornaam, String achternaam) {
        try {
            setCrs();
            crs.setCommand("SELECT klant_id, tussenvoegsel, email, straatnaam, postcode, toevoeging, huisnummer, woonplaats FROM  Klant WHERE voornaam = '" + voornaam + "'AND achternaam = '" + achternaam + "';");
            crs.execute(con);
            while (crs.next()) {
                System.out.println(crs.getString(3));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error reading Klant");
        }

    }

    public void readKlant(String postcode, int huisnummer) {
        try {
            setCrs();
            crs.setCommand("SELECT voornaam, achternaam, tussenvoegsel, email, straatnaam, toevoeging, woonplaats FROM  Klant WHERE postcode = '" + postcode + "'AND huisnummer = '" + huisnummer + "';");
            crs.execute(con);
            while (crs.next()) {
                System.out.println(crs.getString(2));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error readin Klant");
        }

    }

//    public void updateKlant(Klant klant) {
//        try {
//            setCrs();
//            crs.setCommand("SELECT voornaam, achternaam, tussenvoegsel, email, straatnaam, toevoeging, woonplaats FROM  Klant WHERE postcode = '" + postcode + "'AND huisnummer = '" + huisnummer + "';");
//            crs.execute(con);
//            while (crs.next()) {
//                System.out.println(crs.getString(2));
//            }
//        }catch (Exception ex) {
//            ex.printStackTrace();
//            System.out.println("Error readin Klant");
//        }
//    }
    
    public void deleteKlant(int klantId){
        try {
            setCrs();
            crs.setCommand("DELETE FROM Klant WHERE klant_id = '" + klantId + "';");
            crs.execute(con);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error deleting Klant");
        }
    }
    
    public void deleteKlant(String voornaam, String tussenvoegsel, String achternaam){
        try {
            setCrs();
            crs.setCommand("DELETE FROM Klant WHERE voornaam = '" + voornaam + "' AND tussenvoegsel = '" + tussenvoegsel + "' AND achternaam = '" + achternaam + "';");
            crs.execute(con);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error deleting Klant");
        }
    }
    
    public void deleteKlant(String voornaam, String achternaam){
        try {
            setCrs();
            crs.setCommand("DELETE FROM Klant WHERE voornaam = '" + voornaam + "' AND achternaam = '" + achternaam + "';");
            crs.execute(con);
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error deleting Klant");
        }
    }
    
    public void voerSQLUit(String sqlQuery){
        //
        try{
            setCrs();
            crs.setCommand(sqlQuery);
            crs.execute(con);
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error SQL klopt niet");        
        }
    }
}
