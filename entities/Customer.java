package shop.entities;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shop.App;

public class Customer extends User {
    private final ReadOnlyStringWrapper id;
    private final ReadOnlyStringWrapper fullName;
    private final StringProperty phone;
    private final ListProperty<Address> addresses;
    private final ListProperty<Order> orders;

    public Customer(String id, String name, String surname, String phone, String email, String password) {
        super(name, surname, email, password);

        this.id = new ReadOnlyStringWrapper(id);
        this.fullName = new ReadOnlyStringWrapper();
        this.addresses = new SimpleListProperty<>(FXCollections.observableArrayList());
        this.phone = new SimpleStringProperty(phone);
        this.orders = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    public ReadOnlyStringProperty idProperty() {
        return this.id.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty fullNameProperty() {
        return this.fullName.getReadOnlyProperty();
    }

    public ListProperty<Address> addressesProperty() {
        return this.addresses;
    }

    public StringProperty phoneProperty() {
        return this.phone;
    }

    public ListProperty<Order> ordersProperty() {
        return this.orders;
    }

    public String getId() {
        return this.id.get();
    }

    public String getFullName() {
        return this.fullName.get();
    }

    public ObservableList<Address> getAddresses() {
        return this.addresses.get();
    }

    public String getPhone() {
        return this.phone.get();
    }

    public ObservableList<Order> getOrders() {
        return this.orders.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public static Customer findByCredentials(String email, String password) {
        Customer foundCustomer = null;

        for (Customer customer : App.getInstance().getCustomers()) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                foundCustomer = customer;
            }
        }

        return foundCustomer;
    }
}