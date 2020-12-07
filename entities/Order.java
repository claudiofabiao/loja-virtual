package shop.entities;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    private final ReadOnlyIntegerWrapper id;
    private final ReadOnlyObjectWrapper<LocalDate> date;
    private final ReadOnlyObjectWrapper<Customer> customer;
    private final ReadOnlyObjectWrapper<Address> address;
    private final ReadOnlyObjectWrapper<CreditCard> creditCard;
    private final ReadOnlyListWrapper<OrderItem> items;
    private final ReadOnlyObjectWrapper<BigDecimal> total;
    private final ReadOnlyIntegerWrapper status;

    public static final int CANCELED_STATUS = 0;
    public static final int AWAITING_PAYMENT_CONFIRMATION_STATUS = 1;
    public static final int SEPARATING_STATUS = 2;
    public static final int SENT_STATUS = 3;
    public static final int DELIVERED_STATUS = 4;

    public Order(int id, Customer customer, Address address, CreditCard creditCard, ObservableList<OrderItem> items) {
        this.id = new ReadOnlyIntegerWrapper(id);
        this.date = new ReadOnlyObjectWrapper<>(LocalDate.now());
        this.customer = new ReadOnlyObjectWrapper<>(customer);
        this.address = new ReadOnlyObjectWrapper<>(address);
        this.creditCard = new ReadOnlyObjectWrapper<>(creditCard);
        this.items = new ReadOnlyListWrapper<>(FXCollections.unmodifiableObservableList(items));
        this.total = new ReadOnlyObjectWrapper<>(BigDecimal.ZERO);
        this.status = new ReadOnlyIntegerWrapper(AWAITING_PAYMENT_CONFIRMATION_STATUS);

        for (OrderItem item: items) {
            this.total.set(this.total.get().add(item.getTotal()));
        }
    }

    public ReadOnlyIntegerProperty idProperty() {
        return this.id.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<LocalDate> dateProperty() {
        return this.date.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<Customer> customerProperty() {
        return this.customer.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<Address> addressProperty() {
        return this.address.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<CreditCard> creditCardProperty() {
        return this.creditCard.getReadOnlyProperty();
    }

    public ReadOnlyListProperty<OrderItem> itemsProperty() {
        return this.items.getReadOnlyProperty();
    }

    public ReadOnlyObjectProperty<BigDecimal> totalProperty() {
        return this.total.getReadOnlyProperty();
    }

    public ReadOnlyIntegerProperty statusProperty() {
        return this.status.getReadOnlyProperty();
    }

    public int getId() {
        return this.id.get();
    }

    public LocalDate getDate() {
        return this.date.get();
    }

    public Customer getCustomer() {
        return this.customer.get();
    }

    public Address getAddress() {
        return this.address.get();
    }

    public CreditCard getCreditCard() {
        return this.creditCard.get();
    }

    public ObservableList<OrderItem> getItems() {
        return this.items.get();
    }

    public BigDecimal getTotal() {
        return this.total.get();
    }

    public int getStatus() {
        return this.status.get();
    }

    public void setStatus(int status) {
        this.status.set(status);
    }
}