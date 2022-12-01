package Model;

import javafx.scene.control.ComboBox;

import java.time.LocalDateTime;

public class Customers {

    int customerId;
    String customerName;
    String address;
    String postalCode;
    String phone;
    String country;
    int divsionId;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Customers(int customerId, String customerName, String address, String postalCode, String phone, int divsionId, String country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divsionId = divsionId;
        this.country=country;
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


    public int getDivsionId() {
        return divsionId;
    }

    public void setDivsionId(int divsionId) {
        this.divsionId = divsionId;
    }
}