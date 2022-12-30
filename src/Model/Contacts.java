package Model;

/** A class to model the data on the contacts table in the database. */

public class Contacts {

    int contactID;
    String contactName;
    String contactEmail;

    /** Creates a new contact object.
     * @param contactID
     * @param contactName
     * @param contactEmail */

    public Contacts(int contactID, String contactName, String contactEmail) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /** Turns the contact ID into a string of contact ID and name for the contact combox box. */
    @Override
    public String toString (){
        return " ["+ contactID +" ]" +  contactName;
    }
}
