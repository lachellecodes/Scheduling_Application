package Model;

/** A class to model the information on the first level divisions table in the database. */

public class FirstLevelDivisions {

    int divisionID;
    String division;
    int firstLevelDivisionsCountryID;

    /** Creates a new first level division object.
     * @param divisionID
     * @param division
     * @param firstLevelDivisionsCountryID
     * */

    public FirstLevelDivisions(int divisionID, String division, int firstLevelDivisionsCountryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.firstLevelDivisionsCountryID = firstLevelDivisionsCountryID;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getFirstLevelDivisionsCountryID() {
        return firstLevelDivisionsCountryID;
    }

    public void setFirstLevelDivisionsCountryID(int firstLevelDivisionsCountryID) {
        this.firstLevelDivisionsCountryID = firstLevelDivisionsCountryID;
    }

    /** Turns the list of divisions ID numbers into names for the combo box.
     */
    @Override
    public String toString (){
        return division;
    }
}
