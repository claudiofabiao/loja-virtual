package shop.entities;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

abstract class User {
    private final StringProperty name;
    private final StringProperty surname;
    private final ReadOnlyStringWrapper fullName;
    private final StringProperty email;
    private final StringProperty password;

    public User(String name, String surname, String email, String password) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.fullName = new ReadOnlyStringWrapper();
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);

        this.fullName.bind(this.name.concat(" ").concat(this.surname));
    }

    public StringProperty nameProperty() {
        return this.name;
    }

    public StringProperty surnameProperty() {
        return this.surname;
    }

    public ReadOnlyStringProperty fullNameProperty() {
        return this.fullName.getReadOnlyProperty();
    }

    public StringProperty emailProperty() {
        return this.email;
    }

    public StringProperty passwordProperty() {
        return this.password;
    }

    public String getName() {
        return this.name.get();
    }

    public String getSurname() {
        return this.surname.get();
    }

    public String getFullName() {
        return this.fullName.get();
    }

    public String getEmail() {
        return this.email.get();
    }

    public String getPassword() {
        return this.password.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
