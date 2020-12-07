package shop.entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Address {
    private final StringProperty name;
    private final StringProperty zipCode;
    private final StringProperty street;
    private final StringProperty number;
    private final StringProperty city;
    private final StringProperty state;

    public Address(String name, String zipCode, String street, String number, String city, String state) {
        this.name = new SimpleStringProperty(name);
        this.zipCode = new SimpleStringProperty(zipCode);
        this.street = new SimpleStringProperty(street);
        this.number = new SimpleStringProperty(number);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    public StringProperty zipCodeProperty() {
        return this.zipCode;
    }

    public StringProperty streetProperty() {
        return this.street;
    }

    public StringProperty numberProperty() {
        return this.number;
    }

    public StringProperty cityProperty() {
        return this.city;
    }

    public StringProperty stateProperty() {
        return this.state;
    }

    public String getName() {
        return this.name.get();
    }

    public String getZipCode() {
        return this.zipCode.get();
    }

    public String getStreet() {
        return this.street.get();
    }

    public String getNumber() {
        return this.number.get();
    }

    public String getCity() {
        return this.city.get();
    }

    public String getState() {
        return this.state.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setState(String state) {
        this.state.set(state);
    }
}