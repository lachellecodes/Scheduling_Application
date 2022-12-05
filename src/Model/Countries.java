package Model;

public class Countries {

    int countryId;
    String countryName;

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

    @Override
    public String toString (){
        return countryName;
    }
}
