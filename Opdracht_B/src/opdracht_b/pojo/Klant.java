package opdracht_b.pojo;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 3;
        
        hash = 53 * hash + Objects.hashCode(this.voornaam);
        hash = 53 * hash + Objects.hashCode(this.achternaam);
        hash = 53 * hash + Objects.hashCode(this.tussenvoegsel);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.straatnaam);
        hash = 53 * hash + Objects.hashCode(this.postcode);
        hash = 53 * hash + Objects.hashCode(this.toevoeging);
        hash = 53 * hash + this.huisnummer;
        hash = 53 * hash + Objects.hashCode(this.woonplaats);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Klant other = (Klant) obj;
       
        if (!Objects.equals(this.voornaam, other.voornaam)) {
            return false;
        }
        if (!Objects.equals(this.achternaam, other.achternaam)) {
            return false;
        }
        if (!Objects.equals(this.tussenvoegsel, other.tussenvoegsel)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.straatnaam, other.straatnaam)) {
            return false;
        }
        if (!Objects.equals(this.postcode, other.postcode)) {
            return false;
        }
        if (!Objects.equals(this.toevoeging, other.toevoeging)) {
            return false;
        }
        if (this.huisnummer != other.huisnummer) {
            return false;
        }
        if (!Objects.equals(this.woonplaats, other.woonplaats)) {
            return false;
        }
        return true;
    }
}
