package Model;

/** A class to model the information on the customer table in the database. */
public class Customers {

    int customerId;
    String customerName;
    String address;
    String postalCode;
    String phone;
    String country;
    int divisionId;



    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /** The constructor for a new customer object.
     * @param customerId
     * @param customerName
     * @param address
     * @param postalCode
     * @param phone
     * @param divisionId
     * @param country
     * */
    public Customers( int customerId, String customerName, String address, String postalCode, String phone, String country, int divisionId) {
        this.customerId=customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionId = divisionId;
        this.country=country;
    }

    public Customers (){

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /** Displays the customer ID and name as a string in the customer combo box. */
    @Override
    public String toString (){

        return " ["+ customerId +" ]" +  customerName;
    }


}