package opdracht_b;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import opdracht_b.dbconnect.DBConnect;
import opdracht_b.pojo.Klant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Geert
 */
public class AppFunctionality {

    private static CachedRowSet crs;
    private Connection con;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    
    public void setConnection(String url, String user, String password) {
        DBConnect.setUrl(url);
        DBConnect.setUser(user);
        DBConnect.setPassword(password);
    }

    public void setCrs() {
        try {
            crs = new CachedRowSetImpl();
            con = DBConnect.connect();

        } catch (SQLException ex) {
            log.error("Cannot create crs", ex);

        }
    }

    public void createKlant(Klant klant) {
        try {
            log.info("Creating Klant {} {} \n", klant.getVoornaam(), klant.getAchternaam());
            setCrs();
            crs.setCommand("insert into oefen_opdracht_db.Klant (voornaam, achternaam, tussenvoegsel, email, straatnaam, postcode, toevoeging, huisnummer, woonplaats) VALUES ( '"
                    + klant.getVoornaam() + "', '" + klant.getAchternaam() + "', '" + klant.getTussenvoegsel() + "', '" + klant.getEmail() + "', '" + klant.getStraatnaam() + "', '" + klant.getPostcode() + "', '" + klant.getToevoeging() + "', '"
                    + klant.getHuisnummer() + "', '" + klant.getWoonplaats() + "')");
            crs.execute(con);
        } catch (Exception ex) {
            log.error("Cannot insert Klant into database \n", ex);

        }
    }

    public Klant readKlant(int klandId) {
        Klant klant = new Klant();
        try {
            log.info("Reading Klant with id {}\n", klandId);
            setCrs();
            crs.setCommand("SELECT voornaam, achternaam, tussenvoegsel, email, straatnaam, postcode, toevoeging, huisnummer, woonplaats FROM  Klant WHERE klant_id = '" + klandId + "';");
            crs.execute(con);
            while (crs.next()) {
                klant.setVoornaam(crs.getString(1));
                klant.setAchternaam(crs.getString(2));
                klant.setTussenvoegsel(crs.getString(3));
                klant.setEmail(crs.getString(4));
                klant.setStraatnaam(crs.getString(5));
                klant.setPostcode(crs.getString(6));
                klant.setToevoeging(crs.getString(7));
                klant.setHuisnummer(crs.getInt(8));
                klant.setWoonplaats(crs.getString(9));

            }
        } catch (Exception ex) {
            log.error("Cannot read Klant with klantId {}\n", klandId, ex);
        }
        return klant;

    }

    public Klant readKlant(String voornaam, String achternaam) {
        Klant klant = new Klant();
        klant.setVoornaam(voornaam);
        klant.setAchternaam(achternaam);
        try {
            log.info("Reading Klant {} {}\n", voornaam, achternaam);
            setCrs();
            crs.setCommand("SELECT klant_id, tussenvoegsel, email, straatnaam, postcode, toevoeging, huisnummer, woonplaats FROM  Klant WHERE voornaam = '" + voornaam + "'AND achternaam = '" + achternaam + "';");
            crs.execute(con);
            while (crs.next()) {
                klant.setKlant_id(crs.getInt(1));
                klant.setTussenvoegsel(crs.getString(2));
                klant.setEmail(crs.getString(3));
                klant.setStraatnaam(crs.getString(4));
                klant.setPostcode(crs.getString(5));
                klant.setToevoeging(crs.getString(6));
                klant.setHuisnummer(crs.getInt(7));
                klant.setWoonplaats(crs.getString(8));
            }
        } catch (Exception ex) {
            log.error("Cannot read Klan {} {}\n", voornaam, achternaam, ex);
        }
        return klant;

    }

    public Klant readKlant(String postcode, int huisnummer) {
        Klant klant = new Klant();
        klant.setPostcode(postcode);
        klant.setHuisnummer(huisnummer);
        try {
            log.info("Reading Klant with postcode: {} and huisnummer: {}\n", postcode, huisnummer);
            setCrs();
            crs.setCommand("SELECT voornaam, achternaam, tussenvoegsel, email, straatnaam, toevoeging, woonplaats FROM  Klant WHERE postcode = '" + postcode + "'AND huisnummer = '" + huisnummer + "';");
            crs.execute(con);
            while (crs.next()) {
                klant.setVoornaam(crs.getString(1));
                klant.setAchternaam(crs.getString(2));
                klant.setTussenvoegsel(crs.getString(3));
                klant.setEmail(crs.getString(4));
                klant.setStraatnaam(crs.getString(5));
                klant.setToevoeging(crs.getString(6));
                klant.setWoonplaats(crs.getString(7));;
            }
        } catch (Exception ex) {
            log.error("Could not read Klant\n", ex);
        }
        return klant;
    }

    public void updateKlant(Klant klant) {
        try {
            log.info("Updating Klant {} {}\n", klant.getVoornaam(), klant.getAchternaam());
            setCrs();
            crs.setCommand("UPDATE Klant set voornaam = '" + klant.getVoornaam() + "', achternaam = '" + klant.getAchternaam() + "', tussenvoegsel = '" + klant.getTussenvoegsel() + "', email = '" + klant.getEmail()
                    + "', straatnaam = '" + klant.getStraatnaam() + "', postcode = '" + klant.getPostcode() + "', toevoeging = '" + klant.getToevoeging() + "', huisnummer = '" + klant.getHuisnummer()
                    + "', woonplaats = '" + klant.getWoonplaats() + "' where klant_id = " + klant.getKlant_id());
            crs.execute(con);
            while (crs.next()) {
                System.out.println(crs.getString(2));
            }
        } catch (Exception ex) {
            log.error("Cannot Update Klant {} {}\n", klant.getVoornaam(), klant.getAchternaam(), ex);
        }
    }

    public void deleteKlant(int klantId) {
        try {
            log.info("Deleting Klant with klantId {}\n", klantId);
            setCrs();
            crs.setCommand("DELETE FROM Klant WHERE klant_id = '" + klantId + "';");
            crs.execute(con);

        } catch (Exception ex) {
            log.error("Cannot delete Klant\n", ex);
        }
    }

    public void deleteKlant(String voornaam, String tussenvoegsel, String achternaam) {
        try {
            log.info("Deleting Klant {} {} {}\n", voornaam, tussenvoegsel, achternaam);
            setCrs();
            crs.setCommand("DELETE FROM Klant WHERE voornaam = '" + voornaam + "' AND tussenvoegsel = '" + tussenvoegsel + "' AND achternaam = '" + achternaam + "';");
            crs.execute(con);

        } catch (Exception ex) {
            log.error("Cannot delete Klant\n", ex);
        }
    }

    public void deleteKlant(String voornaam, String achternaam) {
        try {
            log.info("Deleting Klant {} {}\n", voornaam, achternaam);
            setCrs();
            crs.setCommand("DELETE FROM Klant WHERE voornaam = '" + voornaam + "' AND achternaam = '" + achternaam + "';");
            crs.execute(con);

        } catch (Exception ex) {
            log.error("Cannot delete Klant\n", ex);
        }
    }

    public void voerSQLUit(String sqlQuery) {
        try {
            log.info("Executing SQL Query\n");
            setCrs();
            crs.setCommand(sqlQuery);
            crs.execute(con);
        } catch (Exception ex) {
            log.error("Cannot execute Query {}\n", sqlQuery, ex);
        }
    }
}
