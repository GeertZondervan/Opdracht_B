package opdracht_b;

import opdracht_b.dbconnect.DBConnect;
import opdracht_b.pojo.Klant;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class AppFunctionalityTest {

    private Klant klant;
    private AppFunctionality functions = new AppFunctionality();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    public AppFunctionalityTest() {
    }

    @Before
    public void setUp() {
        klant = new Klant();
        klant.setVoornaam("Klaas");
        klant.setAchternaam("Graaff");
        klant.setTussenvoegsel("de");
        klant.setEmail("klaasdegraaff@gmail.com");
        klant.setStraatnaam("Straatnaam");
        klant.setPostcode("1234DF");
        klant.setToevoeging("A");
        klant.setHuisnummer(23);
        klant.setWoonplaats("Hilversum");
    }

    @After
    public void tearDown() {
        functions.deleteKlant(klant.getVoornaam(), klant.getAchternaam());
        

    }

    /**
     * Test of setConnection method, of class AppFunctionality.
     */
    @Test
    public void testSetConnection() {
        System.out.println("setConnection");
        String url = "jdbc:mysql://localhost/oefen_opdracht_db";
        String user = "root";
        String password = "rsv1er";
        AppFunctionality instance = new AppFunctionality();
        instance.setConnection(url, user, password);

        assertEquals("Url is the same", url, DBConnect.getUrl());
        assertEquals("user is the same", user, DBConnect.getUser());
        assertEquals("password is the same", password, DBConnect.getPassword());

    }

//    /**
//     * Test of setCrs method, of class AppFunctionality.
//     */
//    @Test
//    public void testSetCrs() {
//        System.out.println("setCrs");
//        AppFunctionality instance = new AppFunctionality();
//        instance.setCrs();
//        DBConnect.setUser("rooot");
//        expectedEx.expect(SQLException.class);
//        expectedEx.expectMessage("Error creating crs");
//    }
    /**
     * Test of createKlant method, of class AppFunctionality.
     */
    @Test
    public void testCreateKlant() {

        System.out.println("createKlant");
        AppFunctionality instance = new AppFunctionality();
        instance.createKlant(klant);

        Klant result = instance.readKlant(klant.getVoornaam(), klant.getAchternaam());

        assertNotNull("klant, must not be null", klant);
        assertNotNull("Result, must not be null", result);
        assertEquals("klant, all fields must be equal", klant, result);

    }

    /**
     * Test of readKlant method, of class AppFunctionality.
     */
    @Test
    public void testReadKlant_int() {
        System.out.println("readKlant klantId");
        functions.createKlant(klant);
        Klant result = functions.readKlant(klant.getVoornaam(), klant.getAchternaam());
        int id = result.getKlant_id();

        Klant result2 = functions.readKlant(id);

        assertNotNull("klant, must not be null", klant);
        assertNotNull("Result2, must not be null", result2);
        assertEquals("klant, all fields must be equal", klant, result2);
    }

    /**
     * Test of readKlant method, of class AppFunctionality.
     */
    @Test
    public void testReadKlant_String_String() {
        System.out.println("readKlant voornaam achternaam");
        functions.createKlant(klant);
        Klant result = functions.readKlant(klant.getVoornaam(), klant.getAchternaam());

        assertNotNull("klant, must not be null", klant);
        assertNotNull("Result, must not be null", result);
        assertEquals("klant, all fields must be equal", klant, result);
    }

    /**
     * Test of readKlant method, of class AppFunctionality.
     */
    @Test
    public void testReadKlant_String_int() {
        System.out.println("readKlant klantId");
        functions.createKlant(klant);
        Klant result = functions.readKlant(klant.getPostcode(), klant.getHuisnummer());

        assertNotNull("klant, must not be null", klant);
        assertNotNull("Result, must not be null", result);
        assertEquals("klant, all fields must be equal", klant, result);
    }

    @Test
    public void testUpdateKlant() {
        System.out.println("UpdateKlant");
        functions.createKlant(klant);

        Klant result = functions.readKlant(klant.getVoornaam(), klant.getAchternaam());
        result.setVoornaam("Henkie");
        result.setAchternaam("Denkie");
        result.setPostcode("5678OO");
        result.setWoonplaats("Utrecht");
        functions.updateKlant(result);

        Klant updatedResult = functions.readKlant(result.getVoornaam(), result.getAchternaam());

        assertNotNull("result, must not be null", result);
        assertNotNull("updatedResult, must not be null", updatedResult);
        assertEquals("voornaam must be equal", result.getVoornaam(), updatedResult.getVoornaam());
        assertEquals("achternaam must be equal", result.getAchternaam(), updatedResult.getAchternaam());
        assertEquals("Postcode must be equal", result.getPostcode(), updatedResult.getPostcode());
        assertEquals("woonplaats must be equal", result.getWoonplaats(), updatedResult.getWoonplaats());
        assertEquals("klant, all fields must be equal", result, updatedResult);
        
        functions.deleteKlant(updatedResult.getKlant_id());
    }

    /**
     * Test of deleteKlant method, of class AppFunctionality.
     */
    @Test
    public void testDeleteKlant_int() {
        System.out.println("Delete Klant klantId");
        functions.createKlant(klant);
        Klant result = functions.readKlant(klant.getVoornaam(), klant.getAchternaam());
        int id = result.getKlant_id();

        functions.deleteKlant(id);

        Klant resultDeleted = functions.readKlant(id);

        assertNull("result is null", resultDeleted.getAchternaam());

    }

    /**
     * Test of deleteKlant method, of class AppFunctionality.
     */
    @Test
    public void testDeleteKlant_3args() {
        System.out.println("Delete Klant Voornaam, tussenvoegsel, achternaam");
        functions.createKlant(klant);
        Klant result = functions.readKlant(klant.getVoornaam(), klant.getAchternaam());
        int id = result.getKlant_id();

        functions.deleteKlant(klant.getVoornaam(), klant.getTussenvoegsel(), klant.getAchternaam());

        Klant resultDeleted = functions.readKlant(id);

        assertNull("result is null", resultDeleted.getAchternaam());

    }

    /**
     * Test of deleteKlant method, of class AppFunctionality.
     */
    @Test
    public void testDeleteKlant_String_String() {
        System.out.println("Delete Klant voornaam achternaam");
        functions.createKlant(klant);
        Klant result = functions.readKlant(klant.getVoornaam(), klant.getAchternaam());
        int id = result.getKlant_id();

        functions.deleteKlant(klant.getVoornaam(), klant.getAchternaam());

        Klant resultDeleted = functions.readKlant(id);

        assertNull("result is null", resultDeleted.getAchternaam());
    }

    /**
     * Test of voerSQLUit method, of class AppFunctionality.
     */
    @Test
    public void testVoerSQLUit() {
        System.out.println("voerSQLUit");
        String sql = "insert into oefen_opdracht_db.Klant (voornaam, achternaam, tussenvoegsel, email, straatnaam, postcode, toevoeging, huisnummer, woonplaats) VALUES ( '"
                + klant.getVoornaam() + "', '" + klant.getAchternaam() + "', '" + klant.getTussenvoegsel() + "', '" + klant.getEmail() + "', '" + klant.getStraatnaam() + "', '" + klant.getPostcode() + "', '" + klant.getToevoeging() + "', '"
                + klant.getHuisnummer() + "', '" + klant.getWoonplaats() + "')";

        functions.voerSQLUit(sql);

        Klant result = functions.readKlant(klant.getVoornaam(), klant.getAchternaam());

        assertNotNull("klant, must not be null", klant);
        assertNotNull("Result, must not be null", result);
        assertEquals("klant, all fields must be equal", klant, result);

    }
    
    @Test
    public void testBuildInsertStatementKlant(){
        System.out.println("Build Insert Statement Klant");
        
        String buildstmt =  functions.buildInsertStatementKlant(klant);
        functions.voerSQLUit(buildstmt);
        
        Klant result = functions.readKlant(klant.getVoornaam(), klant.getAchternaam());
       
        assertNotNull("Result, must not be null", result);
        assertEquals("klant, all fields must be equal", klant, result);
    }
    
    @Test
    public void testBuildInsertStatement(){
        System.out.println("Build Insert Statement");

        String buildstmt =  functions.buildInsertStatement(klant);
        functions.voerSQLUit(buildstmt);
        
        Klant result = functions.readKlant(klant.getVoornaam(), klant.getAchternaam());
       
        assertNotNull("Result, must not be null", result);
        assertEquals("klant, all fields must be equal", klant, result);
    }

    
}
