package opdracht_b.pojo;

import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author Geert
 */
public class Klant {

    private int klant_id;
    private String voornaam;
    private String achternaam;
    private String tussenvoegsel;
    private String email;
    private String straatnaam;
    private String postcode;
    private String toevoeging;
    private int huisnummer;
    private String woonplaats;

//    private SimpleBooleanProperty checkedUpdate = new SimpleBooleanProperty(false);
//    private SimpleBooleanProperty checkedDelete = new SimpleBooleanProperty(false);

    public int getKlant_id() {
        return klant_id;
    }

    public void setKlant_id(int klant_id) {
        this.klant_id = klant_id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

//    public SimpleBooleanProperty checkedUpdateProperty() {
//        return this.checkedUpdate;
//    }
//
//    public java.lang.Boolean getCheckedUpdate() {
//        return this.checkedUpdateProperty().get();
//    }
//
//    public void setCheckedUpdate(final java.lang.Boolean checked) {
//        this.checkedUpdateProperty().set(checked);
//    }
//
//    public SimpleBooleanProperty checkedDeleteProperty() {
//        return this.checkedDelete;
//    }
//
//    public java.lang.Boolean getCheckedDelete() {
//        return this.checkedDeleteProperty().get();
//    }
//
//    public void setCheckedDelete(final java.lang.Boolean checked) {
//        this.checkedDeleteProperty().set(checked);
//    }
}
