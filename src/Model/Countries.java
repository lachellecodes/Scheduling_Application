package Model;

/** A class to model the countries table in the database. */

public class Countries {

    int countryId;
    String countryName;

    /** Creates a new country object.
     * @param countryId
     * @param countryName*/

    public Countries(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryID(int countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return countryName;
    }

    public void setCountry(String country) {
        this.countryName = countryName;
    }

    /** Turns the list of Countries in visible names for the country combo box.*/
    @Override
    public String toString (){
        return countryName;
    }
}
