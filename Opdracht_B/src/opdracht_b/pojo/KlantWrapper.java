package opdracht_b.pojo;

import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class KlantWrapper extends Klant implements Serializable {

    BooleanProperty to_delete = new SimpleBooleanProperty(true);
    Boolean to_update = false;

    private Klant klant;

    public KlantWrapper(Klant klant) {
        this.klant = klant;
    }

    public BooleanProperty to_deleteProperty() {
        return to_delete;
    }

    public boolean getTo_delete() {
        return to_delete.get();
    }

    public void setTo_delete(boolean delete) {
        this.to_delete.set(delete);
    }

//    public Boolean getTo_update() {
//        return to_delete;
//    }
//
//    public void setTo_update(Boolean update) {
//        this.to_delete = update;
//    }
    public int getKlant_id() {
        return getKlant().getKlant_id();
    }

    public void setKlant_id(int klant_id) {
        getKlant().setKlant_id(klant_id);
    }

    public String getVoornaam() {
        return getKlant().getVoornaam();
    }

    public void setVoornaam(String voornaam) {
        getKlant().setVoornaam(voornaam);
    }

    public String getAchternaam() {
        return getKlant().getAchternaam();
    }

    public void setAchternaam(String achternaam) {
        getKlant().setAchternaam(achternaam);
    }

    public String getTussenvoegsel() {
        return getKlant().getTussenvoegsel();
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        getKlant().setTussenvoegsel(tussenvoegsel);
    }

    public String getEmail() {
        return getKlant().getEmail();
    }

    public void setEmail(String email) {
        getKlant().setEmail(email);
    }

    public String getStraatnaam() {
        return getKlant().getStraatnaam();
    }

    public void setStraatnaam(String straatnaam) {
        getKlant().setStraatnaam(straatnaam);
    }

    public String getPostcode() {
        return getKlant().getPostcode();
    }

    public void setPostcode(String postcode) {
        getKlant().setPostcode(postcode);
    }

    public String getToevoeging() {
        return getKlant().getToevoeging();
    }

    public void setToevoeging(String toevoeging) {
        getKlant().setToevoeging(toevoeging);
    }

    public int getHuisnummer() {
        return getKlant().getHuisnummer();
    }

    public void setHuisnummer(int huisnummer) {
        getKlant().setHuisnummer(huisnummer);
    }

    public String getWoonplaats() {
        return getKlant().getWoonplaats();
    }

    public void setWoonplaats(String woonplaats) {
        getKlant().setWoonplaats(woonplaats);
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }
}
